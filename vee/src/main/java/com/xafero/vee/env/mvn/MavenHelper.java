package com.xafero.vee.env.mvn;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.maven.repository.internal.MavenRepositorySystemUtils;
import org.eclipse.aether.DefaultRepositorySystemSession;
import org.eclipse.aether.RepositorySystem;
import org.eclipse.aether.RepositorySystemSession;
import org.eclipse.aether.artifact.DefaultArtifact;
import org.eclipse.aether.collection.CollectRequest;
import org.eclipse.aether.connector.basic.BasicRepositoryConnectorFactory;
import org.eclipse.aether.graph.Dependency;
import org.eclipse.aether.graph.DependencyFilter;
import org.eclipse.aether.impl.DefaultServiceLocator;
import org.eclipse.aether.repository.LocalRepository;
import org.eclipse.aether.repository.LocalRepositoryManager;
import org.eclipse.aether.repository.RemoteRepository;
import org.eclipse.aether.resolution.ArtifactResult;
import org.eclipse.aether.resolution.DependencyRequest;
import org.eclipse.aether.resolution.DependencyResolutionException;
import org.eclipse.aether.resolution.DependencyResult;
import org.eclipse.aether.spi.connector.RepositoryConnectorFactory;
import org.eclipse.aether.spi.connector.transport.TransporterFactory;
import org.eclipse.aether.transport.file.FileTransporterFactory;
import org.eclipse.aether.transport.http.HttpTransporterFactory;

public class MavenHelper {

	protected static final File defaultBaseDir = new File("local_cache");

	protected final RepositorySystem system;
	protected final RepositorySystemSession session;
	protected final List<RemoteRepository> remotes;
	protected final DependencyFilter filter;

	protected LocalRepository localRepo;

	public MavenHelper() {
		defaultBaseDir.mkdirs();
		filter = new SimpleDependencyFilter();
		system = newRepositorySystem();
		session = newSession(system, defaultBaseDir);
		remotes = newRemoteConfiguration();
	}

	protected RepositorySystem newRepositorySystem() {
		DefaultServiceLocator locator = MavenRepositorySystemUtils.newServiceLocator();
		locator.addService(RepositoryConnectorFactory.class, BasicRepositoryConnectorFactory.class);
		locator.addService(TransporterFactory.class, FileTransporterFactory.class);
		locator.addService(TransporterFactory.class, HttpTransporterFactory.class);
		return locator.getService(RepositorySystem.class);
	}

	protected RepositorySystemSession newSession(RepositorySystem system, File baseDir) {
		DefaultRepositorySystemSession session = MavenRepositorySystemUtils.newSession();
		localRepo = new LocalRepository(baseDir);
		LocalRepositoryManager repoMgr = system.newLocalRepositoryManager(session, localRepo);
		session.setLocalRepositoryManager(repoMgr);
		return session;
	}

	protected List<RemoteRepository> newRemoteConfiguration() {
		List<RemoteRepository> remoteRepos = new LinkedList<RemoteRepository>();
		RemoteRepository central = new RemoteRepository.Builder("central", "default", "http://repo1.maven.org/maven2/")
				.build();
		remoteRepos.add(central);
		return remoteRepos;
	}

	protected DependencyResult resolve(String artifactId) throws DependencyResolutionException {
		Dependency root = new Dependency(new DefaultArtifact(artifactId), "runtime");
		CollectRequest collReq = new CollectRequest(root, remotes);
		DependencyRequest depReq = new DependencyRequest(collReq, filter);
		DependencyResult depRes = system.resolveDependencies(session, depReq);
		return depRes;
	}

	public Map<String, File> resolveToFiles(String artifactId) throws DependencyResolutionException {
		Map<String, File> map = new LinkedHashMap<String, File>();
		DependencyResult depRes = resolve(artifactId);
		for (ArtifactResult ar : depRes.getArtifactResults())
			map.put(ar.getArtifact() + "", ar.getArtifact().getFile());
		return map;
	}

	public File getBaseDir() {
		return localRepo.getBasedir();
	}
}