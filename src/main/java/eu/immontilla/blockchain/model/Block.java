package eu.immontilla.blockchain.model;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import com.google.common.hash.Hashing;

public final class Block {
    private String hash;
    private String previousHash;
    private String data;
    private long timestamp;
    private int nonce;

    public Block(String data, String previousHash) {
        super();
        this.data = data;
        this.previousHash = previousHash;
        this.timestamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public String getHash() {
        return hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        System.out.println(String.format("Mining... %s", hash));
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println(String.format("Mined! %s", hash));
    }

    public String calculateHash() {
        StringBuilder sb = new StringBuilder();
        sb.append(previousHash);
        sb.append(Long.toString(timestamp));
        sb.append(Integer.toString(nonce));
        sb.append(data);
        return Hashing.sha256().hashString(sb.toString(), StandardCharsets.UTF_8).toString();
    }

}
