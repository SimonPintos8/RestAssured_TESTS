package DigitalBooking;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage extends BasePage{
    private By btnRegister = By.xpath("//*[@id=\"loginPanel\"]/p[2]/a"); // Boton registrarse
    private By name = By.xpath("//*[@id=\"customer.firstName\"]");
    private By last_name = By.xpath("//*[@id=\"customer.lastName\"]");
    private By address = By.xpath("//*[@id=\"customer.address.street\"]");
    private By city = By.xpath("//*[@id=\"customer.address.city\"]");
    private By state = By.xpath("//*[@id=\"customer.address.state\"]");
    private By zipcode = By.xpath("//*[@id=\"customer.address.zipCode\"]");
    private By phone = By.xpath("//*[@id=\"customer.phoneNumber\"]");
    private By ssn = By.xpath("//*[@id=\"customer.ssn\"]");
    private By username = By.xpath("//*[@id=\"customer.username\"]");
    private By password = By.xpath("//*[@id=\"customer.password\"]");
    private By repassword = By.xpath("//*[@id=\"repeatedPassword\"]");
    private By btnCreateAccount = By.xpath("//*[@id=\"customerForm\"]/table/tbody/tr[13]/td[2]/input");

    private By successMessage = By.xpath("//*[@id=\"rightPanel\"]/p");


    /**Constructor de la clase RegisterPage
     * @param driver la instancia de WebDriver utilizada para interactuar con la página web
     */
    public RegisterPage(WebDriver driver, WebDriverWait wait) {
        super(driver, null);
    }

    /** Hace click en el botón "Crear Cuenta".
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public void clickCrearCuenta() throws InterruptedException {
        this.click(btnCreateAccount);
    }

    /** Ingresa el nombre proporcionado en el campo de nombre.
     * @param name el nombre a ingresar en el campo
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public void escribirNombre(String name) throws InterruptedException {
        this.sendText(name, this.name);
    }

    /** Ingresa el apellido proporcionado en el campo de apellido.
     * @param name el apellido a ingresar en el campo
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public void escribirApellido(String name) throws InterruptedException {
        this.sendText(name, last_name);
    }

    public void escribirAddress(String direccion) throws InterruptedException {
        this.sendText(direccion, address);
    }

    public void escribirCity(String ciudad) throws InterruptedException {
        this.sendText(ciudad, city);
    }

    public void escribirState(String estado) throws InterruptedException {
        this.sendText(estado, state);
    }

    public void escribirZipcode(String codigoZip) throws InterruptedException {
        this.sendText(codigoZip, zipcode);
    }

    public void escribirPhone(String telefono) throws InterruptedException {
        this.sendText(telefono, phone);
    }

    public void escribirSsn(String sn) throws InterruptedException {
        this.sendText(sn, ssn);
    }

    public void escribirUsername(String nombreUsuario) throws InterruptedException {
        this.sendText(nombreUsuario, username);
    }

    /** Ingresa la contraseña proporcionada en el campo de contraseña.
     * @param pass la contraseña a ingresar en el campo
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public void escribirContrasenia(String pass) throws InterruptedException {
        this.sendText(pass, password);
    }

    /** Reingresa la contraseña proporcionada en el campo de confirmación de contraseña.
     * @param pass la contraseña a reingresar en el campo
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public void repetirContrasenia(String pass) throws InterruptedException {
        this.sendText(pass, repassword);
    }

    /** Hace click en el botón "Registrarse".
     *
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public void clickRegistrarse() throws InterruptedException {
        this.click(btnRegister);
    }


    /** Obtiene el texto del mensaje de éxito indicando la creación de la cuenta.
     * @return el texto del mensaje de éxito
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public String cuentaCreadaExito() throws InterruptedException {
        String res = this.getText(successMessage);
        System.out.println("Resultado Card value: " + res);
        return res;
    }

}
