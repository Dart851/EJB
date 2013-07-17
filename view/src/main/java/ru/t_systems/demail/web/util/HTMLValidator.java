package ru.t_systems.demail.web.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.OutputDocument;
import net.htmlparser.jericho.Source;

public class HTMLValidator {

	private static final Set<String> ALLOWED_HTML_TAGS = new HashSet<String>(
			Arrays.asList(HTMLElementName.P, HTMLElementName.BLOCKQUOTE,
					HTMLElementName.H1, HTMLElementName.H2, HTMLElementName.H3,
					HTMLElementName.H4, HTMLElementName.PRE,
					HTMLElementName.STRONG, HTMLElementName.EM,
					HTMLElementName.DEL, HTMLElementName.UL,
					HTMLElementName.LI, HTMLElementName.OL,
					HTMLElementName.SPAN, HTMLElementName.DIV,
					HTMLElementName.TABLE, HTMLElementName.TR,
					HTMLElementName.TD, HTMLElementName.A, HTMLElementName.HR));

	public static String validate(String htmlFragment) {

		Source source = new Source(htmlFragment);

		OutputDocument outputDocument = new OutputDocument(source);

		List<Element> elements = source.getAllElements();

		for (Element element : elements) {

			if (!ALLOWED_HTML_TAGS.contains(element.getName())) {

				outputDocument.remove(element.getStartTag());
				System.out.println("-----------------------END TAG"
						+ element.getEndTag());
				
				if (!element.getStartTag().isSyntacticalEmptyElementTag()) {

					if (element.getEndTag() != null) {
						outputDocument.remove(element.getEndTag());
					}
				}

			}

		}

		return outputDocument.toString();

	}

}
