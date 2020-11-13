package com.xingyu.travel.task;

import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.internet.MimeMessage;

/**
 * @Description: 发送邮件任务，实现Runable接口，由线程池执行发送邮件
 * @Author: Xingyu
 * @PackageName: com.xiupeilian.carpart.task
 * @Version: 1.0
 **/
public class mailTask implements Runnable {
	private JavaMailSender mailSender;
	private MimeMessage message;

	public mailTask() {
	}

	public mailTask(JavaMailSender mailSender, MimeMessage message) {
		this.mailSender = mailSender;
		this.message = message;
	}

	@Override
	public void run() {
		mailSender.send(message);
	}
}
