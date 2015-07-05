package com.xafero.vee.env.annots;

import java.net.URI;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xafero.vee.env.Environment;

@SupportedSourceVersion(SourceVersion.RELEASE_6)
@SupportedAnnotationTypes(value = { "com.xafero.vee.env.annots.Require" })
public class RequireProcessor extends AbstractProcessor implements Processor {

	private static final Logger log = LoggerFactory.getLogger("ecj");

	private final Environment env;

	public RequireProcessor() {
		super();
		env = new Environment();
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment round) {
		for (Element e : round.getElementsAnnotatedWith(Require.class)) {
			Require req = e.getAnnotation(Require.class);
			for (String reqStr : req.value()) {
				URI reqUri = URI.create(reqStr);
				log.debug("Found requirement '{}'...", reqUri);
				try {
					env.require(reqUri);
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}
			}
		}
		return true;
	}
}