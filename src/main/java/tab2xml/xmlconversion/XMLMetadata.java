package tab2xml.xmlconversion;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;

import tab2xml.IntRange;
import tab2xml.model.TimeSignature;

/**
 * The metadata specified by the {@code EditingPanel}.
 *
 * @author Adrien Hopkins
 * @since 2021-03-22
 */
public final class XMLMetadata {
	/**
	 * A builder that can be used to build XML metadata.
	 *
	 * @since 2021-03-22
	 */
	public static final class Builder {
		/** Title of score */
		private String title;
		/** Composer of score */
		private Optional<String> composer;
		
		/**
		 * Time signature data. Each measure is mapped to a time signature.
		 */
		private final SortedMap<Integer, TimeSignature> timeSignatures;
		
		/**
		 * Creates the Builder using a default title and empty time-signature
		 * data.
		 * 
		 * @since 2021-03-22
		 */
		public Builder() {
			this(DEFAULT_TITLE);
		}
		
		/**
		 * Creates the Builder using a title and empty time-signature data.
		 *
		 * @param title title to use
		 * @since 2021-03-22
		 */
		public Builder(String title) {
			this.title = Objects.requireNonNull(title, "title may not be null");
			this.composer = Optional.empty();
			this.timeSignatures = new TreeMap<>();
		}
		
		/**
		 * @return {@code XMLMetadata} instance with same parameters as this
		 *         Builder.
		 * @since 2021-03-22
		 */
		public XMLMetadata build() {
			return new XMLMetadata(this.title, this.composer,
					new TreeMap<>(this.timeSignatures));
		}
		
		/**
		 * Sets the composer
		 *
		 * @param composer new composer
		 * @return this builder
		 * @since 2021-03-31
		 */
		public Builder composer(String composer) {
			this.composer = Optional.ofNullable(composer);
			return this;
		}
		
		/**
		 * Sets the time signature of a range of measures. Any exsting time
		 * signatures in this range will be overwritten.
		 *
		 * @param start     start of measure range
		 * @param stop      end of measure range
		 * @param signature time signature
		 * @return this builder
		 * @since 2021-03-31
		 */
		public Builder setTimeSignature(int start, int stop,
				TimeSignature signature) {
			for (int measure = start; measure <= stop; measure++) {
				this.timeSignatures.put(measure, signature);
			}
			return this;
		}
		
		/**
		 * Sets the title
		 *
		 * @param title new title
		 * @return this builder
		 * @since 2021-03-22
		 */
		public Builder title(String title) {
			this.title = Objects.requireNonNull(title, "title may not be null");
			return this;
		}
	}
	
	/** The default title for a score */
	public static final String DEFAULT_TITLE = "Untitled Score";
	
	/**
	 * @return {@code XMLMetadata} instance with default title and nothing else
	 * @since 2021-03-26
	 */
	public static final XMLMetadata fromDefaultTitle() {
		return new Builder().build();
	}
	
	/**
	 * Returns an {@code XMLMetadata} instance with data about the provided title
	 * and nothing else.
	 *
	 * @param title title to use
	 * @return instance
	 * @since 2021-03-26
	 */
	public static final XMLMetadata fromTitle(String title) {
		return new Builder(title).build();
	}
	
	/**
	 * Title of score
	 */
	private final String title;
	/**
	 * Composer of score
	 */
	private final Optional<String> composer;
	/**
	 * Time signature data
	 */
	private final SortedMap<Integer, TimeSignature> timeSignatures;
	
	/**
	 * Time signature data in int-range form
	 */
	private transient Map<IntRange, TimeSignature> timeSignatureRanges = null;
	
	/**
	 * @param title          title of score
	 * @param timeSignatures time signature data
	 * @since 2021-03-22
	 */
	private XMLMetadata(String title, Optional<String> composer,
			SortedMap<Integer, TimeSignature> timeSignatures) {
		this.title = title;
		this.composer = composer;
		this.timeSignatures = timeSignatures;
	}
	
	/**
	 * @return the composer
	 * @since 2021-03-31
	 */
	public final Optional<String> getComposer() {
		return this.composer;
	}
	
	/**
	 * @return map mapping ranges of measure numbers to time signatures. Ranges
	 *         not explicitly mapped to time signatures will not be present in
	 *         the returned map.
	 * @since 2021-03-31
	 */
	public final Map<IntRange, TimeSignature> getTimeSignatureRanges() {
		if (this.timeSignatureRanges == null) {
			final Map<IntRange, TimeSignature> timeSignatureRanges = new HashMap<>();
			TimeSignature prevSignature = null;
			int prevRangeBeginning = 1;
			final int maxValue = this.timeSignatures.keySet().stream()
					.mapToInt(Integer::intValue).max().orElse(0);
			
			// iterating over values, calculating the ranges
			for (int i = 1; i <= maxValue; i++) {
				final TimeSignature currentSignature = this.timeSignatures.get(i);
				
				if (i != 1 && !Objects.equals(currentSignature, prevSignature)) {
					// put previous range into map
					final IntRange range = IntRange.valueOf(prevRangeBeginning, i);
					timeSignatureRanges.put(range, prevSignature);
					prevRangeBeginning = i;
				}
				
				prevSignature = currentSignature;
			}
			
			// put in last range
			final IntRange range = IntRange.valueOf(prevRangeBeginning,
					maxValue + 1);
			timeSignatureRanges.put(range, prevSignature);
			
			// remove ranges that map to null
			final Map<IntRange, TimeSignature> nullFreeRanges = new HashMap<>();
			for (final IntRange key : timeSignatureRanges.keySet()) {
				if (timeSignatureRanges.get(key) != null) {
					nullFreeRanges.put(key, timeSignatureRanges.get(key));
				}
			}
			
			// store map for later
			this.timeSignatureRanges = Collections.unmodifiableMap(nullFreeRanges);
		}
		
		return this.timeSignatureRanges;
	}
	
	/**
	 * @return A map mapping the measure number to the time signature. If a time
	 *         signature was not specified for a measure number, this map may not
	 *         contain it, or it may map it to {@code null}.
	 * @since 2021-03-22
	 */
	public final Map<Integer, TimeSignature> getTimeSignatures() {
		return Collections.unmodifiableMap(this.timeSignatures);
	}
	
	/**
	 * @return the title
	 * @since 2021-03-22
	 */
	public final String getTitle() {
		return this.title;
	}
}
