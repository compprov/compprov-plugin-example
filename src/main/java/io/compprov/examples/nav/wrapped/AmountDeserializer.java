package io.compprov.examples.nav.wrapped;

import io.compprov.examples.nav.model.Amount;
import io.compprov.examples.nav.model.Currency;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.deser.std.StdDeserializer;

import java.math.BigDecimal;

/**
 * Jackson 3.x ({@code tools.jackson.*}) deserializer for {@link Amount}, reading the
 * {@code {"currency": ..., "amount": ...}} shape produced when an {@code Amount} value is
 * serialized into a CPG snapshot. Registered alongside {@link AmountWrapper} via
 * {@code environment.registerWrapper(Amount.class, new AmountWrapper(), new AmountDeserializer())}
 * so that snapshots containing {@code Amount} input values can be deserialized and replayed.
 * <p>
 * {@code Amount} might just as well be represented as a record, but here we deliberately keep
 * it a plain class to demonstrate how to register a custom deserializer for a domain type.
 */
public class AmountDeserializer extends StdDeserializer<Amount> {

    public AmountDeserializer() {
        super(Amount.class);
    }

    @Override
    public Amount deserialize(JsonParser p, DeserializationContext ctxt) throws JacksonException {
        JsonNode node = ctxt.readTree(p);
        Currency currency = Currency.valueOf(node.get("currency").asString());
        BigDecimal amount = new BigDecimal(node.get("amount").asString());
        return new Amount(currency, amount);
    }
}
