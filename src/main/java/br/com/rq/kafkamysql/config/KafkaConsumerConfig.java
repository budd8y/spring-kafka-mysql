package br.com.rq.kafkamysql.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import br.com.rq.kafkamysql.domain.Person;
import br.com.rq.kafkamysql.kafka.consumer.Consumer;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

	@Value("${kafka.bootstrapServers}")
	private String bootstratServers;
	
	@Value("${kafka.topics.groupId}")
	private String groupId;
	
	@Value("${kafka.topics.autoOffsetResetConfig}")
	private String autoOffsetResetConfig;
	
	@Bean
	public Map<String, Object> consumerConfigs() {
		Map<String, Object> props = new HashMap<String, Object>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstratServers);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetResetConfig);
		
		return props;
	}
	
	@Bean
	public ConsumerFactory<String, Object> consumerFactory() {
		return new DefaultKafkaConsumerFactory<String, Object>(consumerConfigs(), new StringDeserializer(), new JsonDeserializer<>(Object.class));
	}
	
	@Bean
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Object>> kafkaListenerContainerFactory(){
		ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}
	
	@Bean ConsumerFactory<String, Person> personConsumerFactory() {
		return new DefaultKafkaConsumerFactory<String, Person>(consumerConfigs(), new StringDeserializer(), new JsonDeserializer<>(Person.class));
	}
	
	@Bean
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Person>> personKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, Person> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(personConsumerFactory());
		return factory;
	}
	
	
	@Bean
	public Consumer consumer() {
		return new Consumer();
	}
	
}
