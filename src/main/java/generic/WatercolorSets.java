package generic;

import static generic.Sets.complement;
import static generic.Sets.difference;
import static generic.Sets.intersection;
import static generic.Sets.union;
import static generic.Watercolors.BRILLIANT_RED;
import static generic.Watercolors.BURNT_UMBER;
import static generic.Watercolors.CERULEAN_BLUE_HUE;
import static generic.Watercolors.VIRIDIAN_HUE;

import java.util.EnumSet;
import java.util.Set;

/**
 * @author kang
 * @date 2023/8/30
 */
public class WatercolorSets {

    public static void main(String[] args) {
        Set<Watercolors> set1 =
            EnumSet.range(BRILLIANT_RED, VIRIDIAN_HUE);
        Set<Watercolors> set2 =
            EnumSet.range(CERULEAN_BLUE_HUE, BURNT_UMBER);
        System.out.println("set1: " + set1);
        System.out.println("set2: " + set2);
        System.out.println(
            "union(set1, set2): " + union(set1, set2));
        Set<Watercolors> subset = intersection(set1, set2);
        System.out.println(
            "intersection(set1, set2): " + subset);
        System.out.println("difference(set1, subset): " +
            difference(set1, subset));
        System.out.println("difference(set2, subset): " +
            difference(set2, subset));
        System.out.println("complement(set1, set2): " +
            complement(set1, set2));
    }
}
