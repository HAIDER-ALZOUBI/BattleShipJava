import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;



public class Client extends Thread{

	
	Socket socketClient;
	
	ObjectOutputStream out;
	ObjectInputStream in;
	ArrayList<String> users;

	boolean canContinue = false;
	// Inside your Client class
	public interface OnUsersListReceivedListener {
		void onReceived(ArrayList<String> usersList);
	}

	private OnUsersListReceivedListener usersListListener;

	public void setOnUsersListReceivedListener(OnUsersListReceivedListener listener) {
		this.usersListListener = listener;
	}


	private Consumer<Serializable> callback;




	Client(Consumer<Serializable> call){
	
		callback = call;
	}

	public void run() {
		try {
			socketClient = new Socket("127.0.0.1", 5555);
			out = new ObjectOutputStream(socketClient.getOutputStream());
			in = new ObjectInputStream(socketClient.getInputStream());
			socketClient.setTcpNoDelay(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

		while (true) {
			synchronized (this) {
				while (!canContinue) {
					try {
						this.wait(); // This line causes the current thread to wait until notify() is called
					} catch (InterruptedException ie) {
						ie.printStackTrace();
					}
				}
			}

			try {
				Object o = in.readObject();
				if(o instanceof Message)
				{
					Message message = (Message) o;
					callback.accept(message);
				}
				else if(o instanceof ArrayList){
					ArrayList<String> usersList = (ArrayList<String>) o;
					if(usersListListener != null) {
						usersListListener.onReceived(usersList);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void setArray(ArrayList<String> users){
		this.users = users;
	}

	public boolean checkUserNameDupe(String username) {
		boolean isDupe = false;
		try {
			out.writeObject(username);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("client");
		}

		try {
			Object o = in.readObject();
			if (o instanceof Boolean) {
				isDupe = (Boolean) o;
				if (!isDupe) {
					synchronized (this) {
						canContinue = true;
						this.notify(); // Notify any waiting threads that they can continue
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDupe;
	}
	public void sendRequest(Message request) {
		try {
			out.writeObject(request);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void send(Message data) {
		
		try {
			out.writeObject(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
