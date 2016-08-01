#!/bin/bash
set -eu
source config.sh # Configure the build with this file.
mkdir -p "build/tmp"
echo "Generating scanner..."
cat \
    'extendj/java4/scanner/Header.flex' \
    'extendj/java4/scanner/Preamble.flex' \
    'extendj/java4/scanner/Macros.flex' \
    'extendj/java4/scanner/RulesPreamble.flex' \
    'extendj/java4/scanner/WhiteSpace.flex' \
    'extendj/java4/scanner/Comments.flex' \
    'extendj/java4/scanner/Keywords.flex' \
    'extendj/java4/scanner/Literals.flex' \
    'extendj/java4/scanner/Operators.flex' \
    'extendj/java4/scanner/Separators.flex' \
    'extendj/java5/scanner/Operators.flex' \
    'extendj/java5/scanner/Keywords.flex' \
    'src/scanner/FreeVariables.flex' \
    'extendj/java5/scanner/Identifiers.flex' \
    'extendj/java4/scanner/Postamble.flex' \
    > "build/tmp/JavaScanner.flex"
mkdir -p "src/gen/java/scanner"
${JFLEX} -d "src/gen/java/scanner" --nobak "build/tmp/JavaScanner.flex"
echo "Generating parser..."
cat \
    'extendj/java4/parser/Header.parser' \
    'extendj/java4/parser/Preamble.parser' \
    'extendj/java4/parser/Java1.4.parser' \
    'extendj/java5/parser/Annotations.parser' \
    'extendj/java5/parser/Generics.parser' \
    'extendj/java5/parser/EnhancedFor.parser' \
    'extendj/java5/parser/java14fix.parser' \
    'extendj/java5/parser/Enums.parser' \
    'extendj/java5/parser/StaticImports.parser' \
    'extendj/java5/parser/GenericMethods.parser' \
    'extendj/java5/parser/VariableArityParameters.parser' \
    'src/parser/FreeVariables.parser' \
    > "build/tmp/JavaParser.all"
${JASTADDPARSER} "build/tmp/JavaParser.all" "build/tmp/JavaParser.beaver"
mkdir -p "src/gen/java/parser"
${BEAVER} -d "src/gen/java/parser" -t -c -w "build/tmp/JavaParser.beaver"
echo "Generating node types and weaving aspects..."
mkdir -p "src/gen/java"
${JASTADD} \
    --package="org.extendj.ast" \
    --o="src/gen/java" \
    --rewrite=cnta \
    --safeLazy \
    --beaver \
    --visitCheck=false \
    --cacheCycle=false \
    'extendj/java4/grammar/Java.ast' \
    'extendj/java4/grammar/NTAFinally.ast' \
    'extendj/java4/grammar/Literals.ast' \
    'extendj/java4/grammar/CatchClause.ast' \
    'extendj/java4/grammar/BoundNames.ast' \
    'extendj/java4/frontend/Variable.jadd' \
    'extendj/java4/frontend/Constant.jadd' \
    'extendj/java4/frontend/Options.jadd' \
    'extendj/java4/frontend/LibCompilationUnits.jadd' \
    'extendj/java4/frontend/StructuredPrettyPrint.jadd' \
    'extendj/java4/frontend/DocumentationComments.jadd' \
    'extendj/java4/frontend/PrettyPrint.jadd' \
    'extendj/java4/frontend/DumpTree.jadd' \
    'extendj/java4/frontend/PathPart.jadd' \
    'extendj/java4/frontend/PositiveLiterals.jrag' \
    'extendj/java4/frontend/NameCheck.jrag' \
    'extendj/java4/frontend/QualifiedNames.jrag' \
    'extendj/java4/frontend/LookupMethod.jrag' \
    'extendj/java4/frontend/AccessControl.jrag' \
    'extendj/java4/frontend/DataStructures.jrag' \
    'extendj/java4/frontend/TypeHierarchyCheck.jrag' \
    'extendj/java4/frontend/TypeAnalysis.jrag' \
    'extendj/java4/frontend/ExceptionHandling.jrag' \
    'extendj/java4/frontend/PrettyPrintUtil.jrag' \
    'extendj/java4/frontend/DefiniteAssignment.jrag' \
    'extendj/java4/frontend/NTAFinally.jrag' \
    'extendj/java4/frontend/ResolveAmbiguousNames.jrag' \
    'extendj/java4/frontend/BranchTarget.jrag' \
    'extendj/java4/frontend/ConstantExpression.jrag' \
    'extendj/java4/frontend/MonitorExit.jrag' \
    'extendj/java4/frontend/ClassfileParser.jrag' \
    'extendj/java4/frontend/BytecodeCONSTANT.jrag' \
    'extendj/java4/frontend/VariableDeclaration.jrag' \
    'extendj/java4/frontend/Modifiers.jrag' \
    'extendj/java4/frontend/Arrays.jrag' \
    'extendj/java4/frontend/DeclareBeforeUse.jrag' \
    'extendj/java4/frontend/TypeCheck.jrag' \
    'extendj/java4/frontend/SyntacticClassification.jrag' \
    'extendj/java4/frontend/ErrorCheck.jrag' \
    'extendj/java4/frontend/LookupConstructor.jrag' \
    'extendj/java4/frontend/ClassPath.jrag' \
    'extendj/java4/frontend/NodeConstructors.jrag' \
    'extendj/java4/frontend/UnreachableStatements.jrag' \
    'extendj/java4/frontend/FrontendMain.jrag' \
    'extendj/java4/frontend/AnonymousClasses.jrag' \
    'extendj/java4/frontend/LookupVariable.jrag' \
    'extendj/java4/frontend/Literals.jrag' \
    'extendj/java4/frontend/LookupType.jrag' \
    'extendj/java4/frontend/PrimitiveTypes.jrag' \
    'extendj/java4/frontend/BoundNames.jrag' \
    'extendj/java5/grammar/VariableArityParameters.ast' \
    'extendj/java5/grammar/StaticImports.ast' \
    'extendj/java5/grammar/Enums.ast' \
    'extendj/java5/grammar/GenericMethods.ast' \
    'extendj/java5/grammar/Annotations.ast' \
    'extendj/java5/grammar/EnhancedFor.ast' \
    'extendj/java5/grammar/Generics.ast' \
    'extendj/java5/frontend/GLBTypeFactory.jadd' \
    'extendj/java5/frontend/PrettyPrint.jadd' \
    'extendj/java5/frontend/GenericMethods.jrag' \
    'extendj/java5/frontend/MethodSignature.jrag' \
    'extendj/java5/frontend/GenericsArrays.jrag' \
    'extendj/java5/frontend/Enums.jrag' \
    'extendj/java5/frontend/GenericBoundCheck.jrag' \
    'extendj/java5/frontend/GenericTypeVariables.jrag' \
    'extendj/java5/frontend/AutoBoxing.jrag' \
    'extendj/java5/frontend/BytecodeDescriptor.jrag' \
    'extendj/java5/frontend/GenericsSubtype.jrag' \
    'extendj/java5/frontend/EnhancedFor.jrag' \
    'extendj/java5/frontend/BytecodeReader.jrag' \
    'extendj/java5/frontend/StaticImports.jrag' \
    'extendj/java5/frontend/GenericsParTypeDecl.jrag' \
    'extendj/java5/frontend/Generics.jrag' \
    'extendj/java5/frontend/BytecodeAttributes.jrag' \
    'extendj/java5/frontend/ReifiableTypes.jrag' \
    'extendj/java5/frontend/GenericMethodsInference.jrag' \
    'extendj/java5/frontend/VariableArityParameters.jrag' \
    'extendj/java5/frontend/BytecodeSignatures.jrag' \
    'extendj/java5/frontend/Annotations.jrag' \
    'extendj/java6/frontend/Override.jrag' \
    'src/frontend/FreeDeclarators.ast' \
    'src/frontend/MuliPrettyPrint.jadd' \
    'src/frontend/ExtensionBase.jrag' \
    'src/frontend/FreeDeclarators.jrag' \
    'extendj/java4/backend/JVMBytecodes.jrag' \
    'extendj/java4/backend/CreateBCode.jrag' \
    'extendj/java4/backend/InnerClasses.jrag' \
    'extendj/java4/backend/Java2Rewrites.jrag' \
    'extendj/java4/backend/NTAFinally.jrag' \
    'extendj/java4/backend/ConstantPoolNames.jrag' \
    'extendj/java4/backend/GenerateClassfile.jrag' \
    'extendj/java4/backend/Transformations.jrag' \
    'extendj/java4/backend/CodeGeneration.jrag' \
    'extendj/java4/backend/MonitorExit.jrag' \
    'extendj/java4/backend/ConstantPool.jrag' \
    'extendj/java4/backend/Attributes.jrag' \
    'extendj/java4/backend/LocalNum.jrag' \
    'extendj/java4/backend/JVMBytecodesDebug.jrag' \
    'extendj/java4/backend/Flags.jrag' \
    'extendj/java5/backend/EnhancedForCodegen.jrag' \
    'extendj/java5/backend/EnclosingMethodAttribute.jrag' \
    'extendj/java5/backend/StaticImportsCodegen.jrag' \
    'extendj/java5/backend/GenericsCodegen.jrag' \
    'extendj/java5/backend/AnnotationsCodegen.jrag' \
    'extendj/java5/backend/AutoBoxingCodegen.jrag' \
    'extendj/java5/backend/EnumsCodegen.jrag' \
    'extendj/java5/backend/Version.jrag' \
    'extendj/java5/backend/VariableArityParametersCodegen.jrag' \
    'src/backend/FreeDeclaratorBCode.jadd' \
    'src/backend/FreeFieldAttribute.jrag' \
    'src/backend/FreeVariablesAttribute.jrag' ${EXTRA_JASTADD_SOURCES}
echo "Compiling Java code..."
mkdir -p build/classes/main
javac -d build/classes/main $(find src/java -name '*.java') \
    $(find src/gen -name '*.java') \
    $(find extendj/src/frontend -name '*.java') ${EXTRA_JAVA_SOURCES}
mkdir -p src/gen-res
echo "moduleName: Muli Lang Backend (based on Java SE 6)" > src/gen-res/BuildInfo.properties
echo "moduleVariant: backend" >> src/gen-res/BuildInfo.properties
echo "timestamp: 2016-08-01T15:47Z" >> src/gen-res/BuildInfo.properties
echo "build.date: 2016-08-01" >> src/gen-res/BuildInfo.properties
jar cef "de.wwu.muli.BackendMain" "muli-lang.jar" \
    -C build/classes/main . \
    -C src/gen-res BuildInfo.properties \
    -C extendj/src/res Version.properties
