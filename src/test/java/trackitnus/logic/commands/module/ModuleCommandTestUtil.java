package trackitnus.logic.commands.module;

import trackitnus.testutil.builder.EditModuleDescriptorBuilder;

public class ModuleCommandTestUtil {
    public static final String VALID_NAME_ONE = "Competitive Programming";
    public static final String VALID_NAME_TWO = "Software Engineer";
    public static final String VALID_CODE_ONE = "CS3233";
    public static final String VALID_CODE_TWO = "CS2103T";

    public static final EditModuleCommand.EditModuleDescriptor DESC_ONE;
    public static final EditModuleCommand.EditModuleDescriptor DESC_TWO;

    static {
        DESC_ONE = new EditModuleDescriptorBuilder().withName(VALID_NAME_ONE).withCode(VALID_CODE_ONE).build();
        DESC_TWO = new EditModuleDescriptorBuilder().withName(VALID_NAME_TWO).withCode(VALID_CODE_TWO).build();
    }
//
//    /**
//     * Updates {@code model}'s filtered list to show only the module at the given {@code targetIndex} in the
//     * {@code model}'s address book.
//     */
//    public static void showModuleAtIndex(Model model, Index targetIndex) {
//        assertTrue(targetIndex.getZeroBased() < model.getFilteredModuleList().size());
//
//        Module module = model.getFilteredModuleList().get(targetIndex.getZeroBased());
//        Predicate<Module> p = t -> t.getName().equals(module.getName());
//        model.updateFilteredModuleList(p);
//
//        assertEquals(1, model.getFilteredModuleList().size());
//    }
}
