package com.paypal.json;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.internal.storage.dfs.DfsRepositoryDescription;
import org.eclipse.jgit.internal.storage.dfs.InMemoryRepository;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.transport.RefSpec;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.eclipse.jgit.treewalk.filter.PathFilter;


public class GitDownloadFile {

	private static final String REMOTE_URL = "https://github.com/packiarajN/jsoncompare.git";
    private static final String BRANCH = "master";
    private static final String FILE_TO_READ = "test.txt";
	private static final int DEFAULT_BUFFER_SIZE = 1024;


    public static void main(String[] args) throws IOException, GitAPIException {
        DfsRepositoryDescription repoDesc = new DfsRepositoryDescription();
        InMemoryRepository repo = new InMemoryRepository(repoDesc);
        Git git = new Git(repo);
        git.fetch()
                .setRemote(REMOTE_URL)
                .setRefSpecs(new RefSpec("+refs/heads/*:refs/heads/*"))
                .call();
        repo.getObjectDatabase();
        ObjectId lastCommitId = repo.resolve("refs/heads/" + BRANCH);
        RevWalk revWalk = new RevWalk(repo);
        RevCommit commit = revWalk.parseCommit(lastCommitId);
        RevTree tree = commit.getTree();
        TreeWalk treeWalk = new TreeWalk(repo);
        treeWalk.addTree(tree);
        treeWalk.setRecursive(true);
        treeWalk.setFilter(PathFilter.create(FILE_TO_READ));
        if (!treeWalk.next()) {
            return;
        }
        ObjectId objectId = treeWalk.getObjectId(0);
        ObjectLoader loader = repo.open(objectId);
        //loader.copyTo(System.out);
        InputStream in = loader.openStream();
        File file = new File("servicename.txt");

        copyInputStreamToFile(in, file);

    }
    
    private static void copyInputStreamToFile(InputStream inputStream, File file)
            throws IOException {

        // append = false
        try (FileOutputStream outputStream = new FileOutputStream(file, false)) {
            int read;
            byte[] bytes = new byte[DEFAULT_BUFFER_SIZE];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        }

    }

}