# Project2CS252

This project is about mining strategies. In this project we will implement three
strategies discussed in class.



--------------------------------------------------------------------------------------------------
Simulation Model
The simulation uses a simplified model of the real Bitcoin network world to simulate the mining and
propagation of blocks. A fixed number of blocks will be simulated. You do not need to understand all
the details of the simulation but the most important points are
1. The simulation proceeds in discrete iterations where each iteration consists of a mining round
and propagation round.2.
Each miner draws a creation time from Exp[hashRate] and the miner with the lowest
creationtime mines a block. The probability of mining a block in each iteration for a miner i is,
thus:
3. With small probability a second block gets mined at the same time (by the miner who drew
the second smallest number). Both blocks will be propagated at the same time. With even
smaller probability a third block is mined and so on.
4. If a miner s wants to broadcast a block he draws transmission times for each other miner r
from Exp[connectivity s * connectivity r ] . If two miners s 1 and s 2 broadcast a block to r at the
same time then the probability of s 1 ’s block arriving first is:
5. If a miner upon receiving a block wants to broadcast a new block, they draw a set of
transmission times for the other agents as in 4. but delayed by the current time.
Consequently, there exists a first mover advantage in block propagation.
6. The simulation ends at randomized times, using an exponential distribution.
-------------------------------------------------------------------------------------------

We implement 3 mining strategies : 

 1. MajorityMiner : that performs a 51% attack if it is capable. A 51% in this context means extracting as much relative
profit as possible.

2. SelfishMiner that performs a temporary block withholding attack if profitable.

3. FeeSnipingMiner that forks to try stealing unusually valuable blocks when profitable. That is, when a block with an 
unusually large transaction fee is mined by a competitor, this miner should temporarily reject that block and try to
re-mine a longer fork where it keeps the large transaction fee for itself.
