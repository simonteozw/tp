package trackitnus.logic.commands.module;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import trackitnus.commons.core.Messages;
import trackitnus.logic.commands.CommandResult;
import trackitnus.logic.commands.exceptions.CommandException;
import trackitnus.model.ReadOnlyTrackIter;
import trackitnus.model.TrackIter;
import trackitnus.model.commons.Code;
import trackitnus.model.module.Module;
import trackitnus.testutil.Assert;
import trackitnus.testutil.ModelStub;
import trackitnus.testutil.builder.ModuleBuilder;

public class AddModuleCommandTest {

    @Test
    public void constructor_nullModule_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new AddModuleCommand(null));
    }

    @Test
    public void execute_moduleAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingModuleAdded modelStub = new ModelStubAcceptingModuleAdded();
        Module validModule = new ModuleBuilder().build();

        CommandResult commandResult = new AddModuleCommand(validModule).execute(modelStub);

        assertEquals(String.format(Messages.MESSAGE_ADD_MODULE_SUCCESS, validModule),
            commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validModule), modelStub.modulesAdded);
    }

    @Test
    public void execute_duplicateModule_throwsCommandException() {
        Module validModule = new ModuleBuilder().build();
        AddModuleCommand addModuleCommand = new AddModuleCommand(validModule);
        ModelStub modelStub = new ModelStubWithModule(validModule);

        Assert.assertThrows(CommandException.class,
            Messages.MESSAGE_DUPLICATE_MODULE, () -> addModuleCommand.execute(modelStub));
    }

    /**
     * A Model stub that contains a single module.
     */
    private static class ModelStubWithModule extends ModelStub {
        private final Module module;

        ModelStubWithModule(Module module) {
            requireNonNull(module);
            this.module = module;
        }

        @Override
        public boolean hasModule(Module module) {
            requireNonNull(module);
            return this.module.hasSameCode(module);
        }
    }

    /**
     * A Model stub that always accepts the module being added.
     */
    private static class ModelStubAcceptingModuleAdded extends ModelStub {
        final ArrayList<Module> modulesAdded = new ArrayList<>();

        @Override
        public boolean hasModule(Module module) {
            requireNonNull(module);
            return modulesAdded.stream().anyMatch(module::hasSameCode);
        }

        @Override
        public boolean hasModule(Code code) {
            return true;
        }

        @Override
        public boolean canAddMoreModule() {
            return true;
        }

        @Override
        public void addModule(Module module) {
            requireNonNull(module);
            modulesAdded.add(module);
        }

        @Override
        public ReadOnlyTrackIter getTrackIter() {
            return new TrackIter();
        }
    }
}
