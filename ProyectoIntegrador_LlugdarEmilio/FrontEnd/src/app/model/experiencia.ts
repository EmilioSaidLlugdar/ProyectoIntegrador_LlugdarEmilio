export class Experiencia {
    // replicamos el Entity que tenemos en la BD
    id? :number;
    nombreE: string;
    descripcionE: string;

    constructor (nombreE: string, descripcionE:string){
        this.nombreE=nombreE;
        this.descripcionE=descripcionE;
        
    }

}
