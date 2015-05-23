package net.teamfps.wot;

import java.util.regex.*;

/**
 * 
 * @author Zekye
 *
 */
public class Font {

	// class Typography {
	// public Typography(String name,String ) {}
	// }

	public static void renderString(String str, int fsize, int x, int y) {
		if (str.length() <= 0) return;
		String[] split = str.split("");
		for (int i = 0; i < split.length; i++) {
			String n = split[i];
			Sprite s = getChar(n);
			if (s != null) {
				Bitmap.renderSprite(s, x + i * (fsize + 1), y, fsize, fsize + 4);
			}
		}
	}

	private static Sprite getChar(String str) {
		Sprite[] chars = isTypography(str) ? Sprite.chars : (isUpperCase(str) ? Sprite.chars_upper : Sprite.chars_lower);
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] != null) {
				String name = chars[i].name;
				if (name.contains(".png")) {
					String s = name.substring(0, name.length() - 4);
					String ss = isTypography(str) ? Typography(str) : str;
					if (s.equals(ss)) {
						return chars[i];
					}
				}
			}
		}
		return null;
	}

	private static final String regex = "^[A-Z0-9]"; // alpha-numeric uppercase

	public static boolean isUpperCase(String str) {
		return Pattern.compile(regex).matcher(str).find();
	}

	private static String Typography(String str) {
		if (str.equals(":")) {
			return "colon";
		} else if (str.equals(";")) {
			return "semicolon";
		} else if (str.equals("[")) {
			return "bracket_right";
		} else if (str.equals("]")) {
			return "bracket_left";
		} else if (str.equals("<")) {
			return "greater_than_left";
		} else if (str.equals(">")) {
			return "greater_than_right";
		} else if (str.equals("=")) {
			return "equal";
		} else if (str.equals(".")) {
			return "period";
		} else if (str.equals(",")) {
			return "comma";
		} else if (str.equals("?")) {
			return "question_mark";
		} else if (str.equals("-")) {
			return "dash";
		} else if (str.equals("_")) {
			return "underscore";
		} else if (str.equals("(")) {
			return "parenthese_left";
		} else if (str.equals(")")) {
			return "parenthese_right";
		} else if (str.equals("{")) {
			return "curly_left";
		} else if (str.equals("}")) {
			return "curly_right";
		}
		return str;
	}

	public static boolean isTypography(String str) {
		String[] nums = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ":", ";", "[", "]", "<", ">", ".", ",", "=", "-", "_", "?", "(", ")", "{", "}" };
		for (String s : nums) {
			if (str.equals(s)) {
				return true;
			}
		}
		return false;
	}
}
