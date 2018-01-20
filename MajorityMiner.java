package in.ac.iitk.cse.cs252.miners;


import in.ac.iitk.cse.cs252.blockchain.Block;
import in.ac.iitk.cse.cs252.blockchain.NetworkStatistics;

public class MajorityMiner extends BaseMiner implements Miner{

    private Block currentHead, myHead;
    private int totalHashRate=1, myHashRate=1;

    public MajorityMiner(String id, int hashRate, int connectivity) {
        super(id, hashRate, connectivity);
        this.myHashRate = hashRate;
    }

    @Override
    public Block currentlyMiningAt() {
        return myHead;
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
                this.myHead = block;
            }
        }
        else {
            if (currentHead == null) {
                this.currentHead = block;
                this.myHead = block;
            }
            else if (block != null &&  ( ((1.0*myHashRate)/(totalHashRate+1) < 0.51) || (block.getHeight() - myHead.getHeight() > 6) )) {
                this.currentHead = block;
                this.myHead = block;
            }

        }
    }


    @Override
    public void initialize(Block genesis, NetworkStatistics networkStatistics) {
        this.myHead = genesis;
    }

    @Override
    public void networkUpdate(NetworkStatistics statistics) {

        this.totalHashRate = statistics.getTotalHashRate();
    }
}
