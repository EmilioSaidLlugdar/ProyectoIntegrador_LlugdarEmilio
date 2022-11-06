
export class persona{
    id?: number; // le decimos que id no es necesario, por que es autogenerado
    nombre: string;
    apellido: string;
    descripcion: string;
    img: string;

    //generamos un contructor para que?
    constructor(nombre: string, apellido: string, descripcion: string, img: string){
        this.nombre = nombre;
        this.apellido= apellido;
        this.descripcion= descripcion;
        this.img = img;
    }
}