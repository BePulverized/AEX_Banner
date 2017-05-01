package Classes.RMI;

import java.beans.PropertyChangeEvent;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by BePulverized on 30-4-2017.
 */
public class Publisher {
    
    private HashMap<String, Set<IRemotePropertyListener>> listeners;
    
    private String properties;
    
    public Publisher(String[] properties)
    {
        listeners = new HashMap<String, Set<IRemotePropertyListener>>();
        listeners.put(null, new HashSet<IRemotePropertyListener>());
        for(String s : properties){
            listeners.put(s, new HashSet<IRemotePropertyListener>());
        }
        setProperties();
        
    }

    private void setProperties() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ ");
        Iterator<String> it = listeners.keySet().iterator();
        sb.append(it.next());
        while (it.hasNext()) {
            sb.append(", ").append(it.next());
        }
        sb.append(" }");
        properties = sb.toString();
    }

    public void addListener(IRemotePropertyListener listener, String property){
        checkInBehalfOfProgrammer(property);
        listeners.get(property).add(listener);
    }

    public void removeListener(IRemotePropertyListener listener, String property) {
        if (property != null) {
            Set<IRemotePropertyListener> propertyListeners = listeners.get(property);
            if (propertyListeners != null) {
                propertyListeners.remove(listener);
                listeners.get(null).remove(listener);
            }
        } else { //property == null, dus alle abonnementen van listener verwijderen
            Set<String> keyset = listeners.keySet();
            for (String key : keyset) {
                listeners.get(key).remove(listener);
            }
        }
    }

    public void inform(Object source, String property, Object oldValue, Object newValue) {
        checkInBehalfOfProgrammer(property);

        Set<IRemotePropertyListener> alertable;
        alertable = listeners.get(property);
        if (property != null) {
            alertable.addAll(listeners.get(null));
        } else {
            Set<String> keyset = listeners.keySet();
            for (String key : keyset) {
                alertable.addAll(listeners.get(key));
            }
        }

        for (IRemotePropertyListener listener : alertable) {

            PropertyChangeEvent evt = new PropertyChangeEvent(
                    source, property, oldValue, newValue);
            try {
                listener.propertyChange(evt);
            } catch (RemoteException ex) {
                removeListener(listener, null);
                Logger.getLogger(Publisher.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public void addProperty(String property) {
        if (property.equals("")) {
            throw new RuntimeException("a property cannot be an empty string");
        }

        if (listeners.containsKey(property)) {
            return;
        }

        listeners.put(property, new HashSet<IRemotePropertyListener>());
        setProperties();
    }

    public void removeProperty(String property) {
        checkInBehalfOfProgrammer(property);

        if (property != null) {
            listeners.remove(property);
        } else {
            Set<String> keyset = listeners.keySet();
            for (String key : keyset) {
                if (key != null) {
                    listeners.remove(key);
                }
            }
        }
        setProperties();
    }

    public Iterator<String> getProperties() {
        return listeners.keySet().iterator();
    }

    private void checkInBehalfOfProgrammer(String property) throws RuntimeException{

            if (!listeners.containsKey(property)) {
                throw new RuntimeException("property " + property + " is not a "
                        + "published property, please make a choice out of: "
                        + properties);
            }
    }


}
