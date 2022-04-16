package dev.arcmaksim.game

import dev.arcmaksim.game.domain.Creature

class CreatureFactory {

    fun productSkeletonWarrior(): Creature = Creature(
        name = "Skeleton Warrior",
        iconResId = R.drawable.creature_skeleton_warrior,
        description = "This medium creature appears to be nothing but a set of bones. Pinpoints of red light smolder in its empty eye sockets. The bones animate together while grabbing a weapon and it deftly heads in your direction.",
        difficulty = 1,
        health = 4,
        attack = 1,
        defence = 1,
        xp = 5,
    )

    fun productSkeletonArcher(): Creature = Creature(
        name = "Skeleton Archer",
        iconResId = R.drawable.creature_skeleton_archer,
        description = "This medium creature appears to be nothing but a set of bones. Pinpoints of red light smolder in its empty eye sockets. The bones animate together while grabbing a weapon and it deftly heads in your direction.",
        difficulty = 1,
        health = 3,
        attack = 2,
        defence = 0,
        xp = 7,
    )

    fun productSkeletonWarlord(): Creature = Creature(
        name = "Skeleton Warlord",
        iconResId = R.drawable.creature_skeleton_warlord,
        description = "This medium creature appears to be nothing but a set of bones. Pinpoints of red light smolder in its empty eye sockets. The bones animate together while grabbing a weapon and it deftly heads in your direction.",
        difficulty = 3,
        health = 8,
        attack = 3,
        defence = 2,
        xp = 15,
    )

}
