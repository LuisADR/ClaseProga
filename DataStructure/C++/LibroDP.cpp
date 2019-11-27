#include <string>
using std::string;

class LibroDP{
  //atributos
  private:
  string titulo, autor, aditorial;

  public:
  LibroDP(){
    titulo = "COSMOS";
    autor = "Sagan";
    editorial = "Planeta";
  }

  LibroDP(string datos){

    char strDatos[80];

    strcpy(strDatos, datos.c_str());

    titulo = strtok(strDatos, "_");
    autor = strtok(strDatos, "_");
    editorial = strtok(strDatos, "_");

  }

  string toString(){
    return titulo + "*" + autor + "*" + editorial;
  }
}

int main(){

  LibroDP *librodp = new LibroDP("LA ODISEA_HOMERO_PORRUA");
  cout << librodp-<toString() + \n;
}
