package it.grid.storm.srm.types;

/**
 * @author Michele Dibenedetto
 */
public class RecursionLevel {

	private final Recursion recursionType;
	private final Integer maxLevel;
	private final Integer startingDepth;

	private static final Integer defaultLevel = 3;
	private static final Integer defaultDepth = 0;
	public static final RecursionLevel noRecursion = new RecursionLevel(
		Recursion.NONE);

	public RecursionLevel(Recursion recursionType, Integer level,
		Integer startingDepth) throws IllegalArgumentException {

		if (recursionType == null
			|| (recursionType.isSpecificable() && (level == null || level <= 0))
			|| (!recursionType.isSpecificable() && (level != null && level > 0))
			|| (recursionType == Recursion.NONE && ((startingDepth != null && startingDepth > 0)))) {
			throw new IllegalArgumentException(
				"Unable to build the object, illegal arguments: " + "recursionType="
					+ recursionType + " ,  level=" + level + " , startingDepth="
					+ startingDepth);
		}
		this.recursionType = recursionType;
		this.maxLevel = level;
		this.startingDepth = startingDepth;
	}

	public RecursionLevel(Recursion recursionType, Integer level) {

		if (recursionType == null
			|| (recursionType.isSpecificable() && (level == null || level < 0))
			|| (!recursionType.isSpecificable() && (level != null && level > 0))) {
			throw new IllegalArgumentException(
				"Unable to build the object, illegal arguments: " + "recursionType="
					+ recursionType + " ,  level=" + level);
		}
		if (recursionType != Recursion.NONE) {
			this.startingDepth = defaultDepth;
		} else {
			this.startingDepth = null;
		}
		this.maxLevel = level;
		this.recursionType = recursionType;
	}

	public RecursionLevel(Recursion recursionType) {

		if (recursionType.isSpecificable()) {
			this.maxLevel = defaultLevel;
		} else {
			this.maxLevel = null;
		}
		if (recursionType != Recursion.NONE) {
			this.startingDepth = defaultDepth;
		} else {
			this.startingDepth = null;
		}
		this.recursionType = recursionType;
	}

	public boolean isRecursionAllowed() {

		return !this.recursionType.equals(Recursion.NONE);
	}

	public boolean isRecursionLimited() {

		return !this.recursionType.equals(Recursion.FULL);
	}

	public Integer getRecursionLevel() throws IllegalStateException {

		if (!recursionType.isSpecificable()) {
			throw new IllegalStateException(
				"Unable to get recursion level on Recursion \'" + recursionType
					+ "\' . Not available");
		}
		return this.maxLevel;
	}

	public boolean hasStartingDepth() throws IllegalStateException {

		if (!isRecursionAllowed()) {
			throw new IllegalStateException(
				"Unable to get  starting recursion level on Recursion \'"
					+ recursionType + "\' . Not available");
		}
		return this.startingDepth != null;
	}

	public Integer getStartingDepth() throws IllegalStateException {

		if (!hasStartingDepth()) {
			throw new IllegalStateException(
				"Unable to get starting recursion level. Not specified");
		}
		return this.startingDepth;
	}

	@Override
	public String toString() {

		return "RecursionLevel [recursionType=" + recursionType + ", maxLevel="
			+ maxLevel + ", startingDepth=" + startingDepth + "]";
	}

}
