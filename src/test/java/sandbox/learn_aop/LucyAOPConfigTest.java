package sandbox.learn_aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class LucyAOPConfigTest {
	@Bean	public LucyAspectT lucyAspect() {	return new LucyAspectT(); }
	@Bean public LucyTestService lucyTestAPP(){ return new LucyTestService();}
}
