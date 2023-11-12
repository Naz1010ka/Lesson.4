import java.util.Random;

public class Main {
    public static int bossHealth = 700;

    public static int bossDamage = 50;

    public static String bossDefence;

    public static int[] heroesHealth = {280, 270, 250, 260, 350, 300, 302, 270};

    public static int[] heroesDamage = {10, 15, 20, 0, 5, 15, 0, 30};

    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medic", "Golem", "Lucky", "Witсher", "Thor"};
    public static int roundNumber;

    public static void main(String[] args) {
        printStatisticts();
        while (!isGameOver()) {
            playRound();
        }
    }

    public static void playRound() {
        roundNumber++;
        chooseBossDefence();
        bossAttack();
        heroesAttack();
        printStatisticts();
        medic();
        golem();
        lucky();
        witcher();
        thor();
    }

    public static void medic() {
        int point = 50;
        boolean oneRound = false;
        for (int j = 0; j < heroesHealth.length; j++) {
            if (bossHealth >= 0 && heroesHealth[3] > 0) {
                if (heroesHealth[j] < 100 && heroesHealth[j] > 0) {
                    if (!oneRound) {
                        if (heroesAttackType[j] != "Medic") {
                            oneRound = true;
                            heroesHealth[j] = heroesHealth[j] + point;
                            //heroesHealth[j] += point;
                            System.out.println(heroesAttackType[3] + " cured a " + heroesAttackType[j]);
                        }
                    }
                }
            }
        }
    }

    public static void golem() {
        double takesDamage = 0;
        boolean oneHit = false;
        for (int p = 0; p < heroesHealth.length; p++) {
            if (bossHealth >= 0 && heroesHealth[4] > 0) {
                if (heroesAttackType[p] != "Golem") {
                    if (!oneHit) {
                        oneHit = true;
                        takesDamage = bossDamage % heroesHealth[p];
                        heroesHealth[4] -= takesDamage;
                        System.out.println(heroesAttackType[4] + " took boss damage " +
                                takesDamage + " from hero " + heroesAttackType[p]);
                    }
                }
            }
        }
    }

    public static void lucky() {
        Random random = new Random();
        int chance = random.nextInt(10);
        if (chance < 1) {
            System.out.println(heroesAttackType[5] + " dodged the boss's attack");
        } else {
            heroesHealth[5] -= bossDamage;
            if (heroesHealth[5] < 0) {
                heroesHealth[5] = 0;
            }
            System.out.println("Boss hit " + heroesAttackType[5]);
        }
    }

    public static void witcher(){
        for (int l = 0; l < heroesHealth.length; l++){
          if (bossHealth <= 0 && heroesHealth[6] >0){
             if(heroesHealth[l] <= 0) {
                 if (heroesAttackType[l] != heroesAttackType[6]){
                     heroesHealth[l] += heroesHealth[6];
                     System.out.println(heroesAttackType[6] + " revived " + heroesHealth[l]);
                 }
             }
          }
        }
    }
    public static void thor(){
        Random random = new Random();
        boolean stun = random.nextBoolean();
        if (stun ){
            System.out.println(heroesAttackType[7] + " stunned the boss");
            roundNumber++;
        }
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length);
        bossDefence = heroesAttackType[randomIndex];
    }

    public static void heroesAttack() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                int damage = heroesDamage[i];
                if (heroesAttackType[i] == bossDefence) {
                    Random random = new Random();
                    int coeff = random.nextInt(9) + 2;
                    damage = heroesDamage[i] * coeff;
                    System.out.println("Critical damage:  " + damage);
                }
                if (bossHealth - damage < 0) {
                    bossHealth = 0;
                } else
                    bossHealth = bossHealth - damage;
            }
        }
    }

    public static void bossAttack() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
            }
        }
    }

    public static boolean isGameOver() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }


        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void printStatisticts() {
       /* String defence;
        if (bossDefence == null){
            defence = "No defence";
        }else{
            defence = bossDefence;
        }*/
        System.out.println("ROUND " + roundNumber + "----------");
        System.out.println("Boss health " + bossHealth + " damage " + bossDamage +
                " defence " + (bossDefence == null ? "No defence" : bossDefence));

        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] +
                    " health: " + heroesHealth[i] + " damage " + heroesDamage[i]);
        }
    }
}