package com.ravi.springmvcrest.controllers.v1;

import com.ravi.springmvcrest.api.v1.model.CustomerDTO;
import com.ravi.springmvcrest.api.v1.model.CustomerListDTO;
import com.ravi.springmvcrest.domain.Customer;
import com.ravi.springmvcrest.services.CustomerService;
import com.ravi.springmvcrest.services.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CustomerControllerTest extends AbstractRestControllerTest{

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    private String getUrl(Long id){
        return CustomerController.BASE_URL + "/" + id;
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    void getCustomers() throws Exception {
        // when

        when(customerService.getCustomers()).thenReturn(Arrays.asList(new CustomerDTO(), new CustomerDTO()));

        // then
        mockMvc.perform(get(CustomerController.BASE_URL + "/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));
    }

    @Test
    void getCustomerByFirstName() throws Exception {
        // Given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("a");

        when(customerService.getCustomerByFirstName(anyString())).thenReturn(customerDTO);

        // when
        mockMvc.perform(get( CustomerController.BASE_URL + "/firstname/a")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", is("a")));
    }

    @Test
    void getCustomerById() throws Exception {
        // Given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("a");

        when(customerService.getCustomerById(anyLong())).thenReturn(customerDTO);

        // when
        mockMvc.perform(get(getUrl(2L))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", is("a")));
    }

    @Test
    void createNewCustomer() throws Exception {
        // Given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setLastname("b");
        customerDTO.setFirstname("a");

        CustomerDTO savedCustomerDTO = new CustomerDTO();
        savedCustomerDTO.setFirstname(customerDTO.getFirstname());
        savedCustomerDTO.setLastname(customerDTO.getLastname());
        savedCustomerDTO.setUrl( getUrl(1L) );

        when(customerService.createNewCustomer(any())).thenReturn(savedCustomerDTO);

        // when
        mockMvc.perform(post(CustomerController.BASE_URL + "/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstname", equalTo("a")))
                .andExpect(jsonPath("$.lastname", equalTo("b")))
                .andExpect(jsonPath("$.customer_url", equalTo(getUrl(1L))));
    }

    @Test
    void putNewCustomer() throws Exception {
        // Given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("Ravi");
        customerDTO.setLastname("Cheruk");

        CustomerDTO updatedCustomerDTO = new CustomerDTO();
        updatedCustomerDTO.setFirstname(customerDTO.getFirstname());
        updatedCustomerDTO.setLastname(customerDTO.getLastname());
        updatedCustomerDTO.setUrl(getUrl(1L));

        when(customerService.updateNewCustomer(any(), anyLong())).thenReturn(updatedCustomerDTO);

        // when
        mockMvc.perform(put(getUrl(1L))
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo("Ravi")))
                .andExpect(jsonPath("$.lastname", equalTo("Cheruk")))
                .andExpect(jsonPath("$.customer_url", equalTo(getUrl(1L))));
    }

    @Test
    void patchCustomer() throws Exception {
        // Given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("Ravi");
        customerDTO.setLastname("Cheruk");

        CustomerDTO patchedCustomerDTO = new CustomerDTO();
        patchedCustomerDTO.setFirstname(customerDTO.getFirstname());
        patchedCustomerDTO.setLastname("Cherukuri");
        patchedCustomerDTO.setUrl(getUrl(2L));

        when(customerService.patchCustomer(any(), anyLong())).thenReturn(patchedCustomerDTO);

        // when
        mockMvc.perform(patch(getUrl(2L))
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lastname", equalTo("Cherukuri")))
                .andExpect(jsonPath("$.firstname", equalTo(customerDTO.getFirstname())))
                .andExpect(jsonPath("$.customer_url", equalTo(getUrl(2L))));
    }

    @Test
    void deleteCustomer() throws Exception {
        CustomerDTO customerDTO1 = new CustomerDTO();
        customerDTO1.setLastname("last");
        customerDTO1.setFirstname("first");

        CustomerDTO customerDTO2 = new CustomerDTO();
        customerDTO2.setLastname("last");
        customerDTO2.setFirstname("first");

        List<CustomerDTO> customerDTOList = new ArrayList<>();
        customerDTOList.add(customerDTO1);
        customerDTOList.add(customerDTO2);

        when(customerService.getCustomers()).thenReturn(customerDTOList);

        // when
        mockMvc.perform(delete(getUrl(1L))
                .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));
        
        // then
        verify(customerService, times(1)).deleteCustomerById(anyLong());
    }

    @Test
    public void getCustomerByIDNotFound() throws Exception {

        when(customerService.getCustomerById(anyLong())).thenThrow(ResourceNotFoundException.class);

        // when
        mockMvc.perform(get(getUrl(1L))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}