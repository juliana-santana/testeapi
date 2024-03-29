package TesteAPI;

import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class TestApi {

    String enderecoApi = "https://reqres.in/api";
    String recurso = "/users?page=2";
    String recurso1 = "/users/2";
    String recurso3 = "/register";

    @Test
    @DisplayName("Quando pegar lista de cliente com vários clientes cadastrados, Então informar todos os clientes")
    public void pesqusiandoVariosUsuarios(){
        String respostaEsperada = "{\"page\":2,\"per_page\":6," +
                "\"total\":12,\"total_pages\":2," +
                "\"data\":[{\"id\":7,\"email\":\"michael.lawson@reqres.in\"," +
                "\"first_name\":\"Michael\",\"last_name\":\"Lawson\"," +
                "\"avatar\":\"https://reqres.in/img/faces/7-image.jpg\"}," +
                "{\"id\":8,\"email\":\"lindsay.ferguson@reqres.in\"," +
                "\"first_name\":\"Lindsay\",\"last_name\":\"Ferguson\"," +
                "\"avatar\":\"https://reqres.in/img/faces/8-image.jpg\"}," +
                "{\"id\":9,\"email\":\"tobias.funke@reqres.in\"," +
                "\"first_name\":\"Tobias\",\"last_name\":\"Funke\"," +
                "\"avatar\":\"https://reqres.in/img/faces/9-image.jpg\"}," +
                "{\"id\":10,\"email\":\"byron.fields@reqres.in\"," +
                "\"first_name\":\"Byron\",\"last_name\":\"Fields\"," +
                "\"avatar\":\"https://reqres.in/img/faces/10-image.jpg\"}," +
                "{\"id\":11,\"email\":\"george.edwards@reqres.in\"," +
                "\"first_name\":\"George\",\"last_name\":\"Edwards\"," +
                "\"avatar\":\"https://reqres.in/img/faces/11-image.jpg\"}," +
                "{\"id\":12,\"email\":\"rachel.howell@reqres.in\",\"first_name\":\"Rachel\"," +
                "\"last_name\":\"Howell\",\"avatar\":\"https://reqres.in/img/faces/12-image.jpg\"}]," +
                "\"support\":{\"url\":\"https://reqres.in/#support-heading\"," +
                "\"text\":\"To keep ReqRes free, contributions towards server costs are appreciated!\"}}";
        given().
                contentType(JSON).
        when().
                get(enderecoApi + recurso).
        then().
                statusCode(200).
                assertThat().
                body(new IsEqual(respostaEsperada));

    }

    @Test
    @DisplayName("Quando pegar lista de cliente informando apenas um cliente, Então apresentar apenas um cliente")
    public void pesqusandoApenasUmUsuario() {

        String respostaEsperada = "{\"data\":{\"id\":2,\"email\":\"janet.weaver@reqres.in\"," +
                "\"first_name\":\"Janet\",\"last_name\":\"Weaver\"," +
                "\"avatar\":\"https://reqres.in/img/faces/2-image.jpg\"}," +
                "\"support\":{\"url\":\"https://reqres.in/#support-heading\"," +
                "\"text\":\"To keep ReqRes free, contributions towards server costs are appreciated!\"}}";

        given().
                contentType(JSON).
                when().
                get(enderecoApi + recurso1).
                then().
                statusCode(200).
                assertThat().
                body(new IsEqual(respostaEsperada));

    }

    @Test
    @DisplayName("Quando informar usuário e senha, Então usuário id e token gerados")
    public void cadastrarUmCliente(){

        String corpoRequisicao = "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"pistol\"\n" +
                "}";

        String respostaEsperada = "{" +
                "\"id\":4," +
                "\"token\":\"QpwL5tke4Pnpja7X4\"" +
                "}";

        given().
                contentType(JSON).
                body(corpoRequisicao).
        when().
                post(enderecoApi + recurso3).
        then().
                statusCode(200).
                assertThat().
                        body(new IsEqual(respostaEsperada));
    }

    @Test
    @DisplayName("Quando atualizar cliente, Então cliente atualizado com sucesso")
    public void alterarUsuário(){


    }
}

