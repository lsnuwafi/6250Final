//import org.junit.runner.RunWith;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = SpringMvcTestDemoApplication.class)
//@WebAppConfiguration
//public class SpringMvcTestDemoApplicationTests {
//
//    private MockMvc mockMvc;
//
//    @Before
//    public void init() {
//        this.mockMvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
//    }
//
//    @Test
//    public void getUserById() throws Exception {
//        long id = 1;
//        this.mockMvc.perform(get("/users/" + id))
//                .andExpect(status().isOk())
//                .andExpect(content().string("id=" + id));
//    }
//
//}