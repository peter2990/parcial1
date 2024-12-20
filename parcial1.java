import java.util.*;

class Producto {
    private String nombre;
    private double precio;
    private int cantidadInicial;
    private int cantidadVendida;

    // Constructor
    public Producto(String nombre, double precio, int cantidadInicial) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadInicial = cantidadInicial;
        this.cantidadVendida = 0;
    }

    // Métodos getter
    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidadInicial() {
        return cantidadInicial;
    }

    public int getCantidadVendida() {
        return cantidadVendida;
    }

    public int getCantidadDisponible() {
        return cantidadInicial - cantidadVendida;
    }

    // Método para realizar la venta de productos
    public boolean venderProducto(int cantidad) {
        if (cantidad <= getCantidadDisponible()) {
            cantidadVendida += cantidad;
            return true;
        } else {
            System.out.println("No hay suficiente stock de " + nombre + " para vender.");
            return false;
        }
    }

    // Método para duplicar el inventario de un producto cuando se agota
    public void duplicarInventario() {
        if (getCantidadDisponible() == 0) {
            cantidadInicial *= 2;
            System.out.println("El inventario de " + nombre + " ha sido duplicado.");
        }
    }

    // Método para mostrar la información del producto
    public void mostrarInformacion() {
        System.out.println("Producto: " + nombre);
        System.out.println("Precio: $" + precio);
        System.out.println("Cantidad inicial: " + cantidadInicial);
        System.out.println("Cantidad vendida: " + cantidadVendida);
        System.out.println("Cantidad disponible: " + getCantidadDisponible());
    }
}

public class GestionInventario {
    private static Map<String, Producto> inventario = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\nGestión de Inventarios - Tienda de Zapatos");
            System.out.println("1. Ingresar nuevo producto");
            System.out.println("2. Vender producto");
            System.out.println("3. Mostrar inventario");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Limpiar el buffer

            switch (opcion) {
                case 1:
                    // Ingresar nuevo producto
                    System.out.print("Ingrese el nombre del producto: ");
                    String nombre = scanner.nextLine();

                    System.out.print("Ingrese el precio del producto: ");
                    double precio = scanner.nextDouble();

                    System.out.print("Ingrese la cantidad inicial del producto: ");
                    int cantidadInicial = scanner.nextInt();

                    Producto nuevoProducto = new Producto(nombre, precio, cantidadInicial);
                    inventario.put(nombre, nuevoProducto);
                    System.out.println("Producto " + nombre + " ingresado con éxito.");
                    break;

                case 2:
                    // Vender producto
                    System.out.print("Ingrese el nombre del producto a vender: ");
                    nombre = scanner.nextLine();

                    if (inventario.containsKey(nombre)) {
                        System.out.print("Ingrese la cantidad a vender: ");
                        int cantidad = scanner.nextInt();

                        Producto producto = inventario.get(nombre);
                        if (producto.venderProducto(cantidad)) {
                            // Si el inventario se agotó, duplicarlo
                            producto.duplicarInventario();
                        }
                    } else {
                        System.out.println("Producto no encontrado.");
                    }
                    break;

                case 3:
                    // Mostrar inventario
                    System.out.println("\nInventario actual:");
                    for (Producto p : inventario.values()) {
                        p.mostrarInformacion();
                    }
                    break;

                case 4:
                    // Salir
                    continuar = false;
                    System.out.println("¡Gracias por usar el sistema de gestión de inventarios!");
                    break;

                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }

        scanner.close();
    }
}
