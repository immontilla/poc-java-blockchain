package eu.immontilla.blockchain.model;

import java.util.ArrayList;
import java.util.List;

public final class Blockchain {

    private List<Block> blocks;

    public Blockchain(Block genesisBlock) {
        super();
        this.blocks = new ArrayList<Block>();
        this.blocks.add(genesisBlock);
    }

    public void addBlock(Block block) {
        this.blocks.add(block);
    }

    public Block get(int index) {
        return blocks.get(index);
    }

    public boolean isChainValid(int difficulty) {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');
        for (int i = 1; i < blocks.size(); i++) {
            currentBlock = blocks.get(i);
            previousBlock = blocks.get(i - 1);
            if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
                System.out.println("Current Hashes not equal");
                return false;
            }
            if (!previousBlock.getHash().equals(currentBlock.getPreviousHash())) {
                System.out.println("Previous Hashes not equal");
                return false;
            }
            if (!currentBlock.getHash().substring(0, difficulty).equals(hashTarget)) {
                System.out.println("This block hasn't been mined");
                return false;
            }
        }
        return true;
    }

}
