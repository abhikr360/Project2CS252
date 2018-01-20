package in.ac.iitk.cse.cs252.miners;

import in.ac.iitk.cse.cs252.blockchain.Block;
import in.ac.iitk.cse.cs252.blockchain.NetworkStatistics;

public class SelfishMiner extends BaseMiner implements Miner{

    private Block currentHead, myHead;

    public SelfishMiner(String id, int hashRate, int connectivity) {
        super(id, hashRate, connectivity);

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
            if(myHead == null){
                this.myHead = block;
            }
            else if (block != null && block.getHeight() > myHead.getHeight()) {
                this.myHead = block;
                //this.currentHead = block;
            }
        }
        else{
            if(myHead == null) {
                //currentHead = block;
                this.myHead = block;
            }
            else if( block != null && (myHead.getHeight() - block.getHeight() <=1 ) &&  myHead.getHeight() >= block.getHeight() ){
                    this.currentHead = myHead;
            }
            else if(block != null && myHead.getHeight() < block.getHeight()) {
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

    }
}
