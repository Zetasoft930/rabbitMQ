package ao.co.celsosousa.rabbitmq.enums;


public enum RabbitMQConstant {

    FILA_ESTOQUE("ESTOQUE"),
    FILA_PRECO("PRECO");

    private String name;

     RabbitMQConstant(String name){

        this.name = name;
    }

    public String getName() {
        return name;
    }
}
