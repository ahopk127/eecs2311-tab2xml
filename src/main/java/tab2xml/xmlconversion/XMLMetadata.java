package tab2xml.xmlconversion;

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
		 * Time signature data. Each time signature is represented by an entry
		 * mapping the measure it begins on to the time signature
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
			this.timeSignatures = new TreeMap<>();
		}
		
		/**
		 * @return {@code XMLMetadata} instance with same parameters as this
		 *         Builder.
		 * @since 2021-03-22
		 */
		public XMLMetadata build() {
			return new XMLMetadata(this.title, this.composer, null);
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
		 * Sets the time signature of a range of methods.
		 *
		 * @param start     start of measure range
		 * @param stop      end of measure range
		 * @param signature time signature
		 * @return this builder
		 * @since 2021-03-31
		 */
		public Builder setTimeSignature(int start, int stop,
				TimeSignature signature) {
			throw new UnsupportedOperationException("Not implemented yet");
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
	private final Map<IntRange, TimeSignature> timeSignatures;
	
	/**
	 * @param title          title of score
	 * @param timeSignatures time signature data
	 * @since 2021-03-22
	 */
	private XMLMetadata(String title, Optional<String> composer,
			Map<IntRange, TimeSignature> timeSignatures) {
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
	 * @return map mapping ranges of measure numbers to time signatures
	 * @since 2021-03-31
	 */
	public final Map<IntRange, TimeSignature> getTimeSignatureRanges() {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	/**
	 * @return map mapping the measure number to the time signature.
	 * @since 2021-03-22
	 */
	public final Map<Integer, TimeSignature> getTimeSignatures() {
		throw new UnsupportedOperationException("Not implemented yet");
	}
	
	/**
	 * @return the title
	 * @since 2021-03-22
	 */
	public final String getTitle() {
		return this.title;
	}
}
