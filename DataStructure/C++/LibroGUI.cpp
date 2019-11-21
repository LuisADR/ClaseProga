#include <iostream>
using std::cout;
using std::cin;

#include <string>
using std::string;

class LibroGUI{
  private:
  string titulo, autor, editorial;

  public:

  string obtenerDatos(){

    cout << "Titulo: ";
    getline(cin, titulo);
    getline(cin, titulo);
    // cin >> titulo;

    cout << "Autor: ";
    getline(cin, titulo);
    getline(cin, titulo);
    // cin >> autor;

    cout << "Editorial: ";
    getline(cin, titulo);
    getline(cin, titulo);
    // cin >> editorial;

    return titulo + "_" + autor + "_" + editorial;

  }
  void opciones(){

    int opcion = 0;
    string datos, respuesta;

    do{
      //1. Mostrar opciones al usuario
      cout << "\nLinked List:Biblioteca\n";
      cout << "1) Crear nodo en lista\n";
      cout << "2) Consultar Lista\n";
      cout << "5) Salir\n";

      //2. Leer opciones
      cin >> opcion;

      //3. Ejecutar transacciones de acuerdo a la opcion
      if(opcion == 1){
        //Obtener datos del libro
        datos = obtenerDatos();

        //Crear nuevo nodo en la lista utilizando el Objeto correspondiente

        //Desplegar resultado de la transaccion
        cout << datos;
      }
      if(opcion ==2){

      }
    } while(opcion != 5);
  }
};

int main(){
  LibroGUI libro;
  libro.opciones();
}
