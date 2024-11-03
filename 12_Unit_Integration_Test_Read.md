<h1>Unit and Integration Testing</h1>


<h2>JUnit - Java Unit </h2>

1. Test files are not included in final jar, as the scope for test is limited to testing phase only.
2. Setup: Junit - 5
    ```
        <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
   ```
3. Using ```@SpringBootTest``` loads the applicationContext, applicationContext may contains the Bean initialized. which actually run the application for test, which will make the bean also. And if there are any db call or api call that we be hit in real time for testing.
   Using ```@SpringBootTest``` tell spring boot to look for all the bean and store it in spring IOC container, similar as done using ```SpringBootApplication```.
4. Parametrised test: Instead of CsvSource we can also use ```@ValueSource```, also we have ```@ArgumentSource```
   ```
    @ParameterizedTest
    @CsvSource({
            "1, 1, 2",
            "3, 5, 8"
    })
    void testSum(int a, int b, int expected) {
        assertEquals(expected, a+ b);
    }
   
   ```
   Also, instead of parameters we can also use file path as well.

<h2>Mockito</h2>


1. We can also use ```@Autowired``` to inject bean with ```@SpringBootTest```, as ```@SpringBootTest``` will ask spring to create and collect bean at IOC container. Using this will take time, as all the bean and db connections to be made actually real.
2. To avoid using actual service/db/dependency we can simply mock it using ```@Mock```, and Inject these mock to actual place/variables using ```@InjectMocks```. 
3. ```MockBean``` vs ```@Mock``` : https://medium.com/@ykods/difference-between-mock-and-mockbean-in-spring-testing-9576eb312cdb
   1. @Mock : Used when we do not involve using actual Spring Context, used with ```@ExtendWith``` or ```@RunWith```, used in Unit Testing.
   2. @MockBean: It adds the Mock Bean to the Spring Boot Context, hence you can reuse this bean later using ```@Autowired``` or ```@Inject```. Mostly used with ```@SpringBootTest```. Used in IntegrationTesting 
4. Below:
   ```
      @BeforeEach
      void setUp() {
        MockitoAnnotations.initMocks(this);
      }
   ```
   This is run before each test - will initialize and inject the mocks.
5. When to use what ?
   1. In the cases when we need to mock few things and some the dependencies should not be mocked. In this case we would use Spring Context (```@SpringBootTest```)