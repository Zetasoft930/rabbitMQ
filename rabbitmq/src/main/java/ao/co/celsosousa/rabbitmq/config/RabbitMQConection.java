package ao.co.celsosousa.rabbitmq.config;

import ao.co.celsosousa.rabbitmq.enums.RabbitMQConstant;
import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConection {

    private static  final String NOME_EXACHANGE = "amq.direct";

    private AmqpAdmin amqpAdmin;

    public RabbitMQConection(AmqpAdmin amqpAdmin){

        this.amqpAdmin = amqpAdmin;
    }

    private Queue fila(String nome){

    return new Queue(nome,true,false,false);

    }

    private DirectExchange trocaDirecta(){

        return new DirectExchange(NOME_EXACHANGE);
    }

    private Binding relacionamento(Queue fila,DirectExchange troca){

        return  new Binding(fila.getName(), Binding.DestinationType.QUEUE,troca.getName(), fila.getName(), null);
    }


    @PostConstruct
    private void adiciona(){

     Queue filaEstoque =   this.fila(RabbitMQConstant.FILA_ESTOQUE.getName());
     Queue filaPreco =   this.fila(RabbitMQConstant.FILA_PRECO.getName());

     DirectExchange troca = this.trocaDirecta();

     Binding relacionaEstoque =  relacionamento(filaEstoque,troca);
        Binding relacionaPreco =  relacionamento(filaPreco,troca);


        this.amqpAdmin.declareQueue(filaEstoque);
        this.amqpAdmin.declareQueue(filaPreco);

        this.amqpAdmin.declareExchange(troca);

        this.amqpAdmin.declareBinding(relacionaEstoque);
        this.amqpAdmin.declareBinding(relacionaPreco);

    }



}
