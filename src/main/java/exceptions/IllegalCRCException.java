package exceptions;

public class IllegalCRCException extends IllegalArgumentException {
    public IllegalCRCException(){
        super("Illegal CRC");
    }
    public IllegalCRCException(String message){
        super(message);
    }
}
