package dk.ucn.jakubzakandrejkutliakpatrykiciak.parkingservice.messaging

import org.springframework.amqp.core.Queue
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BrokerConfig(
    @Value("\${broker.refreshDataRequest}") val refreshDataRequestQueue: String,
    @Value("\${broker.refreshDataResponse}") val refreshDataResponseQueue: String,
    @Value("\${broker.findParkingRequest}") val findParkingRequestQueue: String,
    @Value("\${broker.findParkingResponse}") val findParkingResponseQueue: String
) {

    @Bean
    fun findParkingRequestQueue(): Queue {
        return Queue(findParkingRequestQueue)
    }

    @Bean
    fun findParkingResponseQueue(): Queue {
        return Queue(findParkingResponseQueue)
    }

    @Bean
    fun refreshDataRequestQueue(): Queue {
        return Queue(refreshDataRequestQueue)
    }

    @Bean
    fun refreshDataResponseQueue(): Queue {
        return Queue(refreshDataResponseQueue)
    }

    @Bean
    fun converter(): MessageConverter {
        return Jackson2JsonMessageConverter()
    }
}
