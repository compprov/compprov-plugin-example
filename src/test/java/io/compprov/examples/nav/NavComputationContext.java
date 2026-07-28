package io.compprov.examples.nav;

import io.compprov.core.DataContext;
import io.compprov.core.DefaultComputationContext;
import io.compprov.core.DefaultComputationEnvironment;
import io.compprov.core.meta.Descriptor;
import io.compprov.examples.nav.model.Amount;
import io.compprov.examples.nav.model.OptionPosition;
import io.compprov.examples.nav.model.Rate;
import io.compprov.examples.nav.wrapped.WrappedAmount;
import io.compprov.examples.nav.wrapped.WrappedOptionPosition;
import io.compprov.examples.nav.wrapped.WrappedRate;
import io.compprov.plugin.example.nav.NavEnvironmentCustomizer;

public class NavComputationContext extends DefaultComputationContext {
    protected static DefaultComputationEnvironment environment;

    static {
        environment = DefaultComputationEnvironment.create();
        new NavEnvironmentCustomizer().customize(environment);
    }

    public NavComputationContext(String name) {
        super(environment, new DataContext(Descriptor.descriptor(name)));
    }

    public WrappedAmount wrap(Amount amount, Descriptor descriptor) {
        return (WrappedAmount) super.wrap(amount, descriptor);
    }

    public WrappedRate wrap(Rate rate, Descriptor descriptor) {
        return (WrappedRate) super.wrap(rate, descriptor);
    }

    public WrappedOptionPosition wrap(OptionPosition position, Descriptor descriptor) {
        return (WrappedOptionPosition) super.wrap(position, descriptor);
    }
}
