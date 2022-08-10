package protobuf;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class Protobuf {

    public static String outputPath = "/Users/moinahmed/Downloads/protoOutput";

    public static void main(String[] args) throws IOException {
        Protobuf protobuf = new Protobuf();

        People people = protobuf.generatePeopleClass();

        protobuf.serializeAndWriteToFile(people);
        protobuf.serializeAndPrint(people);
        protobuf.deserializeAndPrint();
    }

    public void serializeAndWriteToFile(People people) throws IOException {
        Path path = Paths.get(outputPath);

        people.writeTo(Files.newOutputStream(path));
    }

    public void serializeAndPrint(People people) {
        byte [] serializedData = people.toByteArray();

        System.out.println("Serialized Output in Decimal : ");
        System.out.println(Arrays.toString(serializedData));
    }

    public void deserializeAndPrint() throws IOException {
        Path path = Paths.get(outputPath);

        People parsedObject = People.parseFrom(Files.newInputStream(path));
        System.out.println("Deserialized Object : ");
        System.out.println(parsedObject.toString());
    }

    public People generatePeopleClass() {
        Address firstAddress = Address.newBuilder()
                .setStreet("street1")
                .setNumber(100)
                .build();

        Address secondAddress = Address.newBuilder()
                .setStreet("street2")
                .setNumber(101)
                .build();

        Address thirdAddress = Address.newBuilder()
                .setStreet("street3")
                .setNumber(102)
                .build();

        Person firstPerson = Person.newBuilder()
                .setName("person1")
                .addAllAddress(Arrays.asList(firstAddress, secondAddress))
                .addMobile("9999999999")
                .addEmail("person1@example.com")
                .build();

        Person secondPerson = Person.newBuilder()
                .setName("person2")
                .addAddress(thirdAddress)
                .addMobile("8888888888")
                .addEmail("person2@example.com")
                .build();

        People people = People.newBuilder()
                .addPerson(firstPerson)
                .addPerson(secondPerson)
                .build();

        return people;
    }
}
