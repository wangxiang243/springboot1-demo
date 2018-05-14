import com.wx.springbootdemo.CommonTest;
import org.junit.Test;

public class CommonTestC extends CommonTest{

    @Test
    public void test(){
        CommonTest commonTest = new CommonTest();
        commonTest.a();
        CommonTestC commonTestC = new CommonTestC();
        commonTestC.b();
    }

}
