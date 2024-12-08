package net.mobz.client;

public class VanillaClientRegistry {
	/*
	 * blood1 = 0.11F @ bloodCounter < 1000 && bloodCounter > 600 && dryingNumber == 1
	 * blood2 = 0.22F @ bloodCounter < 2000 && bloodCounter >= 1400 && dryingNumber == 2
	 * blood3 = 0.33F @ bloodCounter < 3000 && bloodCounter >= 2000 && dryingNumber == 3
	 * blood4 = 0.44F @ bloodCounter > 3000 && dryingNumber == 4
	 *
	 * blood1dry1 = 0.15F @ bloodCounter <= 600 && bloodCounter > 300 && dryingNumber == 1
	 * blood1dry2 = 0.19F @ bloodCounter <= 300 && bloodCounter > 0 && dryingNumber == 1
	 * blood2dry1 = 0.25F @ bloodCounter <= 1400 && bloodCounter > 600 && dryingNumber == 2
	 * blood2dry2 = 0.29F @ bloodCounter <= 600 && bloodCounter > 0 && dryingNumber == 2
	 * blood3dry1 = 0.35F @ bloodCounter <= 2000 && bloodCounter > 1000 && dryingNumber == 3
	 * blood3dry2 = 0.39F @ bloodCounter <= 1000 && bloodCounter > 0 && dryingNumber == 3
	 * blood4dry1 = 0.45F @ bloodCounter <= 3000 && bloodCounter > 1500 && dryingNumber == 4
	 * blood4dry2 = 0.49F @ bloodCounter <= 1500 && bloodCounter > 0 && dryingNumber == 4
	 */

	/*
	 * blood1 = 0.11F @ (600, 1000) && dryingNumber == 1
	 * blood2 = 0.22F @ [1400, 2000) && dryingNumber == 2
	 * blood3 = 0.33F @ [2000, 3000) && dryingNumber == 3
	 * blood4 = 0.44F @ (3000,) && dryingNumber == 4
	 *
	 * blood1dry1 = 0.15F @ (300, 600] && dryingNumber == 1
	 * blood1dry2 = 0.19F @ (0, 300] && dryingNumber == 1
	 * blood2dry1 = 0.25F @ (600, 1400] && dryingNumber == 2
	 * blood2dry2 = 0.29F @ (0, 600] && dryingNumber == 2
	 * blood3dry1 = 0.35F @ (1000, 2000] && dryingNumber == 3
	 * blood3dry2 = 0.39F @ (0, 1000] && dryingNumber == 3
	 * blood4dry1 = 0.45F @ (1500, 3000] && dryingNumber == 4
	 * blood4dry2 = 0.49F @ (0, 1500] && dryingNumber == 4
	 */

	/*
	 *
	 * blood1dry2 = 0.19F @ (0, 300] && dryingNumber == 1		12	sacrificeknifeblood1dry2
	 * blood1dry1 = 0.15F @ (300, 600] && dryingNumber == 1		11	sacrificeknifeblood1dry1
	 * blood1 = 0.11F @ (600, 1000) && dryingNumber == 1		10	sacrificeknifeblood1
	 *
	 * blood2dry2 = 0.29F @ (0, 600] && dryingNumber == 2		22	sacrificeknifeblood2dry2
	 * blood2dry1 = 0.25F @ (600, 1400] && dryingNumber == 2	21	sacrificeknifeblood2dry1
	 * blood2 = 0.22F @ [1400, 2000) && dryingNumber == 2		20	sacrificeknifeblood2
	 *
	 * blood3dry2 = 0.39F @ (0, 1000] && dryingNumber == 3		32	sacrificeknifeblood3dry2
	 * blood3dry1 = 0.35F @ (1000, 2000] && dryingNumber == 3	31	sacrificeknifeblood3dry1
	 * blood3 = 0.33F @ [2000, 3000) && dryingNumber == 3		30	sacrificeknifeblood3
	 *
	 * blood4dry2 = 0.49F @ (0, 1500] && dryingNumber == 4		42	sacrificeknifeblood4dry2
	 * blood4dry1 = 0.45F @ (1500, 3000] && dryingNumber == 4	41	sacrificeknifeblood4dry1
	 * blood4 = 0.44F @ (3000,) && dryingNumber == 4			40	sacrificeknifeblood4
	 */
}
