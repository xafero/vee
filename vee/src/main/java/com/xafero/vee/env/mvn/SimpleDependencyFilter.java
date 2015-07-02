package com.xafero.vee.env.mvn;

import java.util.Arrays;
import java.util.List;

import org.eclipse.aether.graph.DependencyFilter;
import org.eclipse.aether.graph.DependencyNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleDependencyFilter implements DependencyFilter {

	private static final Logger log = LoggerFactory.getLogger("mvn");

	private final List<String> scopes;

	public SimpleDependencyFilter() {
		scopes = Arrays.asList("compile", "runtime");
	}

	@Override
	public boolean accept(DependencyNode node, List<DependencyNode> parents) {
		boolean accepted = scopes.contains(node.getDependency().getScope());
		log.debug("Accepted '{}' as dependency...", node.getDependency().getArtifact());
		return accepted;
	}
}