package com.paypal.json;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.TransportConfigCallback;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.transport.JschConfigSessionFactory;
import org.eclipse.jgit.transport.OpenSshConfig;
import org.eclipse.jgit.transport.SshSessionFactory;
import org.eclipse.jgit.transport.SshTransport;
import org.eclipse.jgit.transport.Transport;
import org.eclipse.jgit.util.FS;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
public class GitDownload {
  private static final String sshPrivateFilePath = "C:\\Packiaraj\\id_rsa"; // replace with your ssh private
  // key path
  private static final String sshPassword = "Jan@2021"; // replace with your ssh key password
  private static final String sshGitURI = "git@github.com:packiarajN/jsoncompare.git"; // replace with
  // your git
  // project URI
  public static void main(String[] args)
  throws InvalidRemoteException, TransportException, GitAPIException, IOException {
    File workingDir = Files.createTempDirectory("workspace").toFile();
    TransportConfigCallback transportConfigCallback = new SshTransportConfigCallback();
    Git git = Git.cloneRepository().setDirectory(workingDir).setTransportConfigCallback(transportConfigCallback)
      .setURI(sshGitURI).call();
    System.out.println("Project cloned through SSH in: " + workingDir.getPath());
    
    File file = new File(workingDir.getPath()+"\\test.txt");
    
    BufferedReader br = new BufferedReader(new FileReader(file));
    
    String st;
    while ((st = br.readLine()) != null)
      System.out.println(st);
    
  }
  private static class SshTransportConfigCallback implements TransportConfigCallback {
    private final SshSessionFactory sshSessionFactory = new JschConfigSessionFactory() {
      @Override
      protected void configure(OpenSshConfig.Host hc, Session session) {
        session.setConfig("StrictHostKeyChecking", "no");
      }
      @Override
      protected JSch createDefaultJSch(FS fs) throws JSchException {
        JSch jSch = super.createDefaultJSch(fs);
        jSch.addIdentity(sshPrivateFilePath, sshPassword.getBytes());
        return jSch;
      }
    };
    @Override
    public void configure(Transport transport) {
      SshTransport sshTransport = (SshTransport) transport;
      sshTransport.setSshSessionFactory(sshSessionFactory);
    }
  }
}
