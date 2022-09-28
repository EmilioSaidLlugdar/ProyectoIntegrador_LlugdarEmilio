export class LoginUsuario {
    nombreUsuario: String;
    password: String;

    //necesitamos un constructor
    constructor(nombreUsuario: String, password:String){
        this.nombreUsuario=nombreUsuario;
        this.password=password;
    }
}
