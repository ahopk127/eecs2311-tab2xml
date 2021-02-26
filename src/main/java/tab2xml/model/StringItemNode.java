package tab2xml.model;

public class StringItemNode {
	StringItem val;
	StringItemNode next;

	StringItemNode() {
	}

	StringItemNode(StringItem val) {
		this.val = val;
	}

	StringItemNode(StringItem val, StringItemNode next) {
		this.val = val;
		this.next = next;
	}
}
