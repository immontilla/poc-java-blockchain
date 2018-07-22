package eu.immontilla.blockchain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import eu.immontilla.blockchain.model.Block;
import eu.immontilla.blockchain.model.Blockchain;

@SpringBootApplication
public class ConsoleApp implements CommandLineRunner {

    private final Logger LOGGER = LoggerFactory.getLogger(ConsoleApp.class);

    public static void main(String[] args) {
        SpringApplication.run(ConsoleApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        int difficulty = 4;

        Block first = new Block("Lorem ipsum dolor sit amet, consectetur adipiscing elit.", "0");
        Blockchain blockchain = new Blockchain(first);
        blockchain.get(0).mineBlock(difficulty);

        Block second = new Block("In ante risus, scelerisque sit amet sapien eu, gravida lobortis lectus.",
                first.getHash());
        blockchain.addBlock(second);
        blockchain.get(1).mineBlock(difficulty);

        Block third = new Block("Donec dignissim imperdiet elit ut hendrerit.", second.getHash());
        blockchain.addBlock(third);
        blockchain.get(2).mineBlock(difficulty);

        Block fourth = new Block("Sed vitae leo finibus, luctus nunc a, ullamcorper eros.", third.getHash());
        blockchain.addBlock(fourth);
        blockchain.get(3).mineBlock(difficulty);

        LOGGER.info(String.format("Blockchain OK? %s", String.valueOf(blockchain.isChainValid(difficulty))));

        printBlockchain(blockchain);

    }

    private void printBlockchain(Blockchain blockchain) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        LOGGER.info(gson.toJson(blockchain));
    }

}
