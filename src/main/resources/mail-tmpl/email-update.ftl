<div style="background-color:#ECECEC; padding: 35px;">
	<table cellpadding="0" align="center" style="width: 600px; margin: 0px auto; text-align: left; position: relative; border-top-left-radius: 5px; border-top-right-radius: 5px; border-bottom-right-radius: 5px; border-bottom-left-radius: 5px; font-size: 14px; font-family:微软雅黑, 黑体; line-height: 1.5; box-shadow: rgb(153, 153, 153) 0px 0px 5px; border-collapse: collapse; background-position: initial initial; background-repeat: initial initial;background:#fff;">
		<tbody>
			<tr>
				<th valign="middle" style="height: 25px; line-height: 25px; padding: 15px 35px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #6f5499; background-color: #6f5499; border-top-left-radius: 5px; border-top-right-radius: 5px; border-bottom-right-radius: 0px; border-bottom-left-radius: 0px;">
					<font face="微软雅黑" size="5" style="color: rgb(255, 255, 255); ">{webName}</font>
				</th>
			</tr>
			
			<tr>
				<td>
					<div style="padding:25px 35px 40px; background-color:#fff;">
						<h2 style="margin: 5px 0px; ">
							<font color="#333333" style="line-height: 20px; ">
								<font style="line-height: 22px; " size="4">尊敬的 {nickName} ，您好：</font>
							</font>
						</h2>
						<p>当前帐号原邮箱为：{oldEmail}，现申请换绑为：{newEmail}。请确认本邮件是否是您需要的。</p>
						<p>请点击下面的链接，进行邮箱换绑操作：</p>
						<p>
							<a href="{updateEmailUrl}">{updateEmailUrl}</a>
						</p>
						<p>
							如您未提交该申请，请不要理会此邮件，对此为您带来的不便深表歉意。
						</p>
						<p>&nbsp;</p>
						<p align="right">{webName} 官方团队</p>
						<p align="right">{sendDate}</p>
					</div>
				</td>
			</tr>
			
			<tr>
				<td>
					<div style="line-height: 20px;color: #999;background: #f5f5f5;font-size: 12px;border-top: 1px solid #ddd;padding: 10px 20px;">
        				<!-- <p> 
        					如有疑问，致电客服中心 <span style="border-bottom:1px dashed #ccc;z-index:1" t="7" onclick="return false;" data="{webTel}">{webTel}</span>, 方便您解决问题。
            				<br> 
            				或者发邮件到 <a href="mailto:{webEmail}" target="_blank">{webEmail}</a>，感谢您的支持。
        				</p> -->
        				<p> 
        					如有疑问，请发邮件到 <a href="mailto:{webEmail}" target="_blank">{webEmail}</a>，感谢您的支持。
        				</p>
    				</div>
				</td>
			</tr>
		</tbody>
	</table>
</div>