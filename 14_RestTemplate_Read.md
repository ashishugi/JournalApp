<h1>Rest Template</h1>

1. Why needed ? - What will you do in-case you need to call another api from your application and use its response data at you application ?
2. ```@JsonProperty("first_name")``` - Used in POJO, this is to map the actual value present to the current pojo value. Below example means that if you get first_name while deserialization then map it with firstName in POJO and vice-versa.
    ```
        @JsonProperty("first_name")
        private String firstName;
   ```
3. Deserialization: The process of converting JSON object to corresponding POJO (plain old java object) is called deserialization.
4. Serialization: The process of converting POJO to Json is referred as serialization.