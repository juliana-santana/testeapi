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
        String respostaEsperada = "{\"page\":2," +
                "\"per_page\":6," +
                "\"total\":12," +
                "\"total_pages\":2," +
                "\"data\":[{\"id\":7," +
                "\"email\":\"michael.lawson@reqres.in\",\"first_name\":\"Michael\",\"last_name\":\"Lawson\",\"avatar\":\"https://s3.amazonaws.com/uifaces/faces/twitter/follettkyle/128.jpg\"},{\"id\":8,\"email\":\"lindsay.ferguson@reqres.in\",\"first_name\":\"Lindsay\",\"last_name\":\"Ferguson\",\"avatar\":\"https://s3.amazonaws.com/uifaces/faces/twitter/araa3185/128.jpg\"},{\"id\":9,\"email\":\"tobias.funke@reqres.in\",\"first_name\":\"Tobias\",\"last_name\":\"Funke\",\"avatar\":\"https://s3.amazonaws.com/uifaces/faces/twitter/vivekprvr/128.jpg\"},{\"id\":10,\"email\":\"byron.fields@reqres.in\",\"first_name\":\"Byron\",\"last_name\":\"Fields\",\"avatar\":\"https://s3.amazonaws.com/uifaces/faces/twitter/russoedu/128.jpg\"},{\"id\":11,\"email\":\"george.edwards@reqres.in\",\"first_name\":\"George\",\"last_name\":\"Edwards\",\"avatar\":\"https://s3.amazonaws.com/uifaces/faces/twitter/mrmoiree/128.jpg\"},{\"id\":12,\"email\":\"rachel.howell@reqres.in\",\"first_name\":\"Rachel\",\"last_name\":\"Howell\",\"avatar\":\"https://s3.amazonaws.com/uifaces/faces/twitter/hebertialmeida/128.jpg\"}],\"ad\":{\"company\":\"StatusCode Weekly\",\"url\":\"http://statuscode.org/\",\"text\":\"A weekly newsletter focusing on software development, infrastructure, the server, performance, and the stack end of things.\"}}";

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

        String respostaEsperada = "{\"data\":{\"id\":2," +
                "\"email\":\"janet.weaver@reqres.in\"," +
                "\"first_name\":\"Janet\"," +
                "\"last_name\":\"Weaver\"," +
                "\"avatar\":\"https://s3.amazonaws.com/uifaces/faces/twitter/josephstein/128.jpg\"}," +
                "\"ad\":{\"company\":\"StatusCode Weekly\"," +
                "\"url\":\"http://statuscode.org/\"," +
                "\"text\":\"A weekly newsletter focusing on software development, infrastructure, the server, performance, and the stack end of things.\"}}";

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

