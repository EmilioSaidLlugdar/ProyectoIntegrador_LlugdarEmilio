
export class persona{
    id?: number; // le decimos que id no es necesario, por que es autogenerado
    nombre: String;
    apellido: String;
    img: String;

    //generamos un contructor para que?
    constructor(nombre: String, apellido: String, img: String){
        this.nombre = nombre;
        this.apellido= apellido;
        this.img = img;
    }
}