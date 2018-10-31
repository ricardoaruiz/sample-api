package com.rar.sampleapi;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.joda.time.DateTime;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.rar.sampleapi.business.OrderBusiness;
import com.rar.sampleapi.business.domain.IOrder;
import com.rar.sampleapi.business.domain.IOrderItem;
import com.rar.sampleapi.business.domain.OrderImpl;
import com.rar.sampleapi.business.exception.OrderAlreadyExistsException;
import com.rar.sampleapi.db.domain.Order;
import com.rar.sampleapi.db.domain.OrderItem;
import com.rar.sampleapi.rest.OrderRestController;
import com.rar.sampleapi.rest.validation.GlobalControllerErrorHandler;
import com.rar.sampleapi.rest.validation.ValidationMessageKeyEnum;
import com.rar.sampleapi.utils.FileReaderUtil;
import com.rar.sampleapi.utils.MessageResourceUtil;

@WebMvcTest({OrderRestController.class, MessageResourceUtil.class, GlobalControllerErrorHandler.class})
public class OrderRestControllerTest extends AbstractRestControllerTest {

	private static final SimpleDateFormat DAY_MONTH_YEAR = new SimpleDateFormat("dd-MM-yyyy");
	
	private static final String ORDER_RESOURCE_URL = "/order";
	
	private static final String CREATE_ORDER_REQUEST = "./src/test/resource/jsontest/request/create-order.json";
		
	@MockBean
	private OrderBusiness orderBusiness;
		
	@Override
	public void init() {
		System.out.println("Inicialização dos testes");		
	}
		
	/**
	 * Teste de sucesso de listagem de pedidos com resultado
	 * @throws Exception
	 */
	@Test
	public void testListSuccessWithResult() throws Exception {
		
		final List<IOrder> orders = getOrders(false);
		
		Mockito.when(orderBusiness.listAll()).thenReturn(orders);
		
		final ResultActions result = mockMcv.perform(MockMvcRequestBuilders.get(ORDER_RESOURCE_URL));
		result.andExpect(MockMvcResultMatchers.status().isOk());		
		result.andExpect(MockMvcResultMatchers.jsonPath("$.orders", Matchers.hasSize(orders.size())));
		
		for(int i=0; i<orders.size(); i++) {
			IOrder order = orders.get(i);
			result.andExpect(MockMvcResultMatchers.jsonPath("$.orders["+ i +"].titulo", Matchers.is(order.getTitle())));
			result.andExpect(MockMvcResultMatchers.jsonPath("$.orders["+ i +"].criacao", Matchers.is(DAY_MONTH_YEAR.format(order.getCreateAt()))));
			result.andExpect(MockMvcResultMatchers.jsonPath("$.orders["+ i +"].revisao", Matchers.is(DAY_MONTH_YEAR.format(order.getRevisionAt()))));
			result.andExpect(MockMvcResultMatchers.jsonPath("$.orders["+ i +"].valor", Matchers.is(order.getAmount().toString())));
			
			result.andExpect(MockMvcResultMatchers.jsonPath("$.orders["+ i +"].itens", Matchers.hasSize(order.getItems().size())));
			for(int j=0; j<order.getItems().size(); j++) {
				IOrderItem item = order.getItems().get(j);
				result.andExpect(MockMvcResultMatchers.jsonPath("$.orders["+ i +"].itens[" + j + "].descricao", Matchers.is(item.getDescription())));
				result.andExpect(MockMvcResultMatchers.jsonPath("$.orders["+ i +"].itens[" + j + "].quantidade", Matchers.is(item.getQuantity())));
				result.andExpect(MockMvcResultMatchers.jsonPath("$.orders["+ i +"].itens[" + j + "].valor", Matchers.is(item.getAmount().toString())));
				result.andExpect(MockMvcResultMatchers.jsonPath("$.orders["+ i +"].itens[" + j + "].total", Matchers.is(item.getTotalAmount().toString())));
			}
			
		}
		result.andDo(MockMvcResultHandlers.print());
		
	}
	
	/**
	 * Teste de sucesso de listagem de pedidos sem resultado
	 * @throws Exception
	 */
	@Test
	public void testListSuccessWithoutResult() throws Exception {
		
		final List<IOrder> orders = getOrders(true);
		
		Mockito.when(orderBusiness.listAll()).thenReturn(orders);	
		
		final ResultActions result = mockMcv.perform(MockMvcRequestBuilders.get(ORDER_RESOURCE_URL));
		result.andExpect(MockMvcResultMatchers.status().isNoContent());		
	}
	
	/**
	 * Teste de criação de pedido com sucesso
	 * @throws Exception
	 */
	@Test
	public void testCreateSuccess() throws Exception {
				
		final ResultActions result = mockMcv.perform(MockMvcRequestBuilders.post(ORDER_RESOURCE_URL)
					.contentType(MediaType.APPLICATION_JSON)
					.content(FileReaderUtil.getContentsFromFileTest(CREATE_ORDER_REQUEST))
				).andExpect(MockMvcResultMatchers.status().isCreated());
		
		result.andDo(MockMvcResultHandlers.print());
		
	}
	
	/**
	 * Teste de criação de pedido duplicado
	 * @throws Exception
	 */
	@Test
	public void testCreateDuplicatedError() throws Exception {
		
		final String orderTitle = "Pedido 01";
		
		final String errorMessage = messageResourceUtil.getMessage(ValidationMessageKeyEnum.ORDER_ALREADY_EXISTS.getKey(), 
				ValidationMessageKeyEnum.MESSAGE_NOT_FOUND.getKey(), 
				orderTitle);
					
		Mockito.doThrow (
				new OrderAlreadyExistsException(ValidationMessageKeyEnum.ORDER_ALREADY_EXISTS.getKey(), orderTitle)
			).when(orderBusiness).create(Mockito.any());
		
		ResultActions result = mockMcv.perform(MockMvcRequestBuilders.post(ORDER_RESOURCE_URL)
					.contentType(MediaType.APPLICATION_JSON)
					.content(FileReaderUtil.getContentsFromFileTest(CREATE_ORDER_REQUEST))
				).andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());
		
		result.andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is(errorMessage)));		
		result.andDo(MockMvcResultHandlers.print());
		
	}	
	
	/**
	 * Teste de sucesso de consulta de um pedido pelo seu id com retorno 
	 * @throws Exception
	 */
	@Test
	public void testGetOrderSuccessWithResult() throws Exception {
		
		final Long orderId = Long.valueOf(1);
		
		final IOrder order = getOrder(orderId);
		
		Mockito.when(orderBusiness.getOrder(orderId)).thenReturn(order);
		
		ResultActions result = mockMcv.perform(MockMvcRequestBuilders.get(ORDER_RESOURCE_URL + "/" + orderId));
		result.andExpect(MockMvcResultMatchers.status().isOk());		
				
		result.andExpect(MockMvcResultMatchers.jsonPath("$.order.titulo", Matchers.is(order.getTitle())));
		result.andExpect(MockMvcResultMatchers.jsonPath("$.order.criacao", Matchers.is(DAY_MONTH_YEAR.format(order.getCreateAt()))));
		result.andExpect(MockMvcResultMatchers.jsonPath("$.order.revisao", Matchers.is(DAY_MONTH_YEAR.format(order.getRevisionAt()))));
		result.andExpect(MockMvcResultMatchers.jsonPath("$.order.valor", Matchers.is(order.getAmount().toString())));
		
		result.andExpect(MockMvcResultMatchers.jsonPath("$.order.itens", Matchers.hasSize(order.getItems().size())));
		for(int j=0; j<order.getItems().size(); j++) {
			IOrderItem item = order.getItems().get(j);
			result.andExpect(MockMvcResultMatchers.jsonPath("$.order.itens[" + j + "].descricao", Matchers.is(item.getDescription())));
			result.andExpect(MockMvcResultMatchers.jsonPath("$.order.itens[" + j + "].quantidade", Matchers.is(item.getQuantity())));
			result.andExpect(MockMvcResultMatchers.jsonPath("$.order.itens[" + j + "].valor", Matchers.is(item.getAmount().toString())));
			result.andExpect(MockMvcResultMatchers.jsonPath("$.order.itens[" + j + "].total", Matchers.is(item.getTotalAmount().toString())));
		}
		
		result.andDo(MockMvcResultHandlers.print());
		
	}
	
	/**
	 * Teste de sucesso de consulta de um pedido pelo seu id sem retorno
	 * @throws Exception
	 */
	@Test
	public void testGetOrderSuccessWithoutResult() throws Exception {
		
		final Long orderId = Long.valueOf(1);
		
		Mockito.when(orderBusiness.getOrder(orderId)).thenReturn(null);
		
		final ResultActions result = mockMcv.perform(MockMvcRequestBuilders.get(ORDER_RESOURCE_URL + "/" + orderId));
		result.andExpect(MockMvcResultMatchers.status().isNoContent());
		result.andDo(MockMvcResultHandlers.print());
		
	}	
	
	// Inicio dos métodos de suporte aos testes
	
	private List<IOrder> getOrders(boolean isEmpty) {
		
		List<IOrder> list = new ArrayList<IOrder>();
		
		if (!isEmpty) {
			for(int i=1; i<10; i++) {			
				list.add(new OrderImpl(getOrderEntity(Long.valueOf(i))));
			}		
		}
		
		return list;
	}
	
	private IOrder getOrder(Long id) {
		return new OrderImpl(getOrderEntity(id));
	}
	
	private Order getOrderEntity(Long id) {
		Order orderEntity = new Order();
		orderEntity.setId(id);
		orderEntity.setTitle("Pedido " + id);
		orderEntity.setAmount(BigDecimal.valueOf(id * 1000.2));
		orderEntity.setCreateAt(DateTime.now().toDate());
		orderEntity.setRevisionAt(DateTime.now().plusDays(1).toDate());
		
		List<OrderItem> items = new ArrayList<OrderItem>();
		for(int j=1; j<3; j++) {
			Long itemId = Long.valueOf(j);
			BigDecimal amount = BigDecimal.valueOf(itemId * 2);
			OrderItem orderItemEntity = new OrderItem();
			orderItemEntity.setDescription("Item " + itemId);
			orderItemEntity.setId(itemId);
			orderItemEntity.setAmount(amount);
			orderItemEntity.setOrder(orderEntity);
			orderItemEntity.setQuantity(1);
			orderItemEntity.setTotalAmount(amount);
			items.add(orderItemEntity);
		}
		orderEntity.setItems(items);
		return orderEntity;
	}
	
}
