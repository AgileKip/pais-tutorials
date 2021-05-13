package org.agikekip.tutorials.travel;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("org.agikekip.tutorials.travel");

        noClasses()
            .that()
            .resideInAnyPackage("org.agikekip.tutorials.travel.service..")
            .or()
            .resideInAnyPackage("org.agikekip.tutorials.travel.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..org.agikekip.tutorials.travel.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
