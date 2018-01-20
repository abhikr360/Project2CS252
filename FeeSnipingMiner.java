package in.ac.iitk.cse.cs252.miners;

import in.ac.iitk.cse.cs252.blockchain.Block;
import in.ac.iitk.cse.cs252.blockchain.NetworkStatistics;

public class FeeSnipingMiner extends BaseMiner implements Miner{

    private Block currentHead;

    public FeeSnipingMiner(String id, int hashRate, int connectivity) {
        super(id, hashRate, connectivity);

    }

    @Override
    public Block currentlyMiningAt() {
        return currentHead;
    }

    @Override
    public Block currentHead() {
        return currentHead;
    }

    @Override
    public void blockMined(Block block, boolean isMinerMe) {
        if(isMinerMe) {
            if (block.getHeight() > currentHead.getHeight()) {
                this.currentHead = block;
            }
        }
        else{
            if (currentHead == null) {
                currentHead = block;
            } else if (block != null && block.getHeight() > currentHead.getHeight()) {
                if(block.getBlockValue() < 32) {
                    this.currentHead = block;
                }
                else{
                    Block prevBlock = block.getPreviousBlock();
                    if(prevBlock != null) {
                        this.currentHead = prevBlock;
                    }
                    else{
                        this.currentHead = block;
                    }
                }

            }
        }
    }


    @Override
    public void initialize(Block genesis, NetworkStatistics networkStatistics) {
        this.currentHead = genesis;
    }

    @Override
    public void networkUpdate(NetworkStatistics statistics) {

    }



}
